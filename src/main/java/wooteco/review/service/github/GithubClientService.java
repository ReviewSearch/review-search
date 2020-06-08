package wooteco.review.service.github;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import wooteco.review.domain.Comment;
import wooteco.review.domain.PullRequest;
import wooteco.review.properties.GithubProperty;
import wooteco.review.service.github.dto.CommentDto;
import wooteco.review.service.github.dto.PullRequestDto;
import wooteco.review.service.repository.PullRequestRepository;

@Service
public class GithubClientService {
	private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String TEAM_PATH = "repos/woowacourse/";
	private static final String PAGE_SIZE = "&per_page=100";

	private final WebClient webClient;
	private final PullRequestRepository pullRequestRepository;

	public GithubClientService(final GithubProperty githubProperty,
		final PullRequestRepository pullRequestRepository) {
		this.webClient = WebClient.builder()
			.baseUrl(GITHUB_API_BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
			.filter(ExchangeFilterFunctions.basicAuthentication(
				githubProperty.getClientId(), githubProperty.getClientSecret()))
			.build();
		this.pullRequestRepository = pullRequestRepository;
	}

	public List<PullRequestDto> requestPullRequestsBy(final String repositoryName, final State state) {
		if (!existRepoBy(repositoryName)) {
			throw new IllegalArgumentException("유효하지 않은 RepoName: " + repositoryName);
		}
		final Mono<ClientResponse> clientResponse = webClient.get()
			.uri(TEAM_PATH + repositoryName + "/pulls?state=" + state.name().toLowerCase() + PAGE_SIZE)
			.exchange();
		final AtomicInteger lastPage = findLastPage(clientResponse);
		final List<PullRequestDto> pullRequestDtos = listBy(clientResponse, PullRequestDto.class);

		for (int i = 2; i <= lastPage.get(); i++) {
			final String uri =
				TEAM_PATH + repositoryName + "/pulls?state=" + state.name().toLowerCase() + "&page=" + i + PAGE_SIZE;
			pullRequestDtos.addAll(listOfCommunicationBy(uri, PullRequestDto.class));
		}
		return pullRequestDtos;
	}

	private <T> List<T> listOfCommunicationBy(final String uri, final Class<T> convertType) {
		return Objects.requireNonNull(webClient.get()
			.uri(uri)
			.retrieve()
			.bodyToFlux(convertType)
			.collectList()
			.block());
	}

	private <T> List<T> listBy(final Mono<ClientResponse> clientResponse, final Class<T> convertType) {
		return Objects.requireNonNull(
			clientResponse.flatMapMany(response -> response.bodyToFlux(convertType))
				.collectList()
				.block());
	}

	public void updatePullRequest(final String repositoryName) {
		final List<PullRequestDto> pullRequestDtos = requestPullRequestsBy(repositoryName, State.OPEN);
		final List<PullRequestDto> closedPullRequestDtos = requestPullRequestsBy(repositoryName, State.CLOSED);
		final long closedPullRequestCount = pullRequestRepository.countByState(State.CLOSED);
		if (closedPullRequestDtos.size() == closedPullRequestCount) {
			pullRequestDtos.addAll(closedPullRequestDtos);
		}
		final List<PullRequest> pullRequests = new ArrayList<>();

		for (final PullRequestDto pullRequestDto : pullRequestDtos) {
			final PullRequest pullRequest = pullRequestDto.toPullRequest();
			final List<CommentDto> commentDtos = requestCommentsBy(repositoryName, pullRequestDto.getNumber());
			final Set<Comment> comments = commentDtos.stream()
				.map(CommentDto::toComment)
				.collect(Collectors.toSet());

			pullRequests.add(pullRequest.withComments(comments));
		}

		pullRequestRepository.saveAll(pullRequests);
	}

	private AtomicInteger findLastPage(final Mono<ClientResponse> clientResponse) {
		final AtomicInteger lastPage = new AtomicInteger(1);
		clientResponse.subscribe(response -> {
			ClientResponse.Headers headers = response.headers();
			List<String> httpHeaders = headers.header("Link");
			if (httpHeaders.size() != 0) {
				lastPage.set(Integer.parseInt(httpHeaders.get(0).split("page=")[2].substring(0, 1)));
			}
		});
		return lastPage;
	}

	public List<CommentDto> requestCommentsBy(final String repositoryName, final Long pullId) {
		final Mono<ClientResponse> clientResponse = webClient.get()
			.uri(TEAM_PATH + repositoryName + "/pulls/" + pullId + "/comments?" + PAGE_SIZE)
			.exchange();
		final AtomicInteger lastPage = findLastPage(clientResponse);
		final List<CommentDto> commentDtos = listBy(clientResponse, CommentDto.class);

		for (int i = 2; i <= lastPage.get(); i++) {
			String uri = TEAM_PATH + repositoryName + "/pulls/" + pullId + "/comments?page" + i + PAGE_SIZE;
			commentDtos.addAll(listOfCommunicationBy(uri, CommentDto.class));
		}

		return commentDtos;
	}

	public boolean existRepoBy(final String repositoryName) {
		final AtomicBoolean exist = new AtomicBoolean(true);
		webClient.get()
			.uri(TEAM_PATH + repositoryName)
			.exchange()
			.flatMap(it -> {
				if (it.statusCode().isError()) {
					exist.set(false);
				}
				return it.bodyToMono(String.class);
			})
			.block();
		return exist.get();
	}
}

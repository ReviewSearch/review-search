package wooteco.review.service.github;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import wooteco.review.service.github.dto.CommentDto;
import wooteco.review.service.github.dto.PullRequestDto;
import wooteco.review.properties.GithubProperty;

@Service
public class GithubClientService {
	private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String TEAM_PATH = "repos/woowacourse/";

	private final WebClient webClient;

	public GithubClientService(final GithubProperty githubProperty) {
		this.webClient = WebClient.builder()
			.baseUrl(GITHUB_API_BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
			.filter(ExchangeFilterFunctions.basicAuthentication(
				githubProperty.getClientId(), githubProperty.getClientSecret()))
			.build();
	}

	public List<PullRequestDto> requestPullRequestsBy(final String repositoryName) {
		Mono<ClientResponse> clientResponse = webClient.get()
			.uri(TEAM_PATH + repositoryName + "/pulls?state=all")
			.exchange();
		final AtomicInteger lastPage = findLastPage(clientResponse);
		List<PullRequestDto> pullRequestDtos = new ArrayList<>(
			Objects.requireNonNull(
				clientResponse.flatMapMany(response -> response.bodyToFlux(PullRequestDto.class))
					.collectList()
					.block()
			)
		);

		for (int i = 2; i <= lastPage.get(); i++) {
			pullRequestDtos.addAll(Objects.requireNonNull(webClient.get()
				.uri(TEAM_PATH + repositoryName + "/pulls?state=all" + "&page=" + i)
				.retrieve()
				.bodyToFlux(PullRequestDto.class)
				.collectList()
				.block()));
		}
		return pullRequestDtos;
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

	public List<CommentDto> requestCommentsBy(final String repositoryName, final int pullId) {
		return webClient.get()
			.uri(TEAM_PATH + repositoryName + "/pulls/" + pullId + "/comments")
			.retrieve()
			.bodyToFlux(CommentDto.class)
			.collectList()
			.block();
	}
}

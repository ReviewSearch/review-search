package wooteco.review.githubapi;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import wooteco.review.domain.Comment;
import wooteco.review.properties.GithubProperty;

@Service
public class GithubClient {
	private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String PATH_FORMAT = "repos/woowacourse/%s/pulls/%d/comments";

	private final WebClient webClient;

	public GithubClient(final GithubProperty githubProperty) {
		this.webClient = WebClient.builder()
			.baseUrl(GITHUB_API_BASE_URL)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, GITHUB_V3_MIME_TYPE)
			.filter(ExchangeFilterFunctions.basicAuthentication(
				githubProperty.getClientId(), githubProperty.getClientSecret()))
			.build();
	}

	public List<Comment> listCommentsBy(final String repositoryName, final int pullId) {
		return webClient.get()
			.uri(String.format(PATH_FORMAT, repositoryName, pullId))
			.retrieve()
			.bodyToFlux(Comment.class)
			.collectList()
			.block();
	}
}

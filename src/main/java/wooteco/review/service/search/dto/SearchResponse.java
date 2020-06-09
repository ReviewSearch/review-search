package wooteco.review.service.search.dto;

import java.util.List;
import java.util.stream.Collectors;

import wooteco.review.domain.Comment;

public class SearchResponse {
	private String login;
	private String content;
	private String htmlUrl;

	private SearchResponse() {
	}

	public SearchResponse(String login, String content, String htmlUrl) {
		this.login = login;
		this.content = content;
		this.htmlUrl = htmlUrl;
	}

	public static List<SearchResponse> listOf(List<Comment> comments) {
		return comments.stream()
			.map(comment -> new SearchResponse(comment.getLogin(), comment.getContent(), comment.getHtmlUrl()))
			.collect(Collectors.toList());
	}

	public String getLogin() {
		return login;
	}

	public String getContent() {
		return content;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}
}

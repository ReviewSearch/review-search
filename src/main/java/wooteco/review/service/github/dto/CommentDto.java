package wooteco.review.service.github.dto;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import wooteco.review.domain.Comment;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentDto {
	@NotNull
	private Long id;

	@NotNull
	private String login;

	@JsonProperty("body")
	@NotNull
	private String content;

	@NotNull
	private LocalDateTime updatedAt;

	@NotNull
	private String htmlUrl;

	public Comment toComment() {
		return Comment.of(id, login, content, updatedAt, htmlUrl);
	}

	@JsonProperty("user")
	public void unpackLogin(Map<String, String> user) {
		login = user.get("login");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

}
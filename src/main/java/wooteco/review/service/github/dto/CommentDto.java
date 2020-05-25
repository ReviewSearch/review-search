package wooteco.review.service.github.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import wooteco.review.domain.Comment;

@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentDto {
	@NotNull
	private String diffHunk;

	@JsonProperty("body")
	@NotNull
	private String content;

	@NotNull
	private LocalDateTime updatedAt;

	@NotNull
	private String htmlUrl;

	public Comment toComment() {
		return Comment.of(diffHunk, content, updatedAt, htmlUrl);
	}

	public String getDiffHunk() {
		return diffHunk;
	}

	public void setDiffHunk(String diffHunk) {
		this.diffHunk = diffHunk;
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
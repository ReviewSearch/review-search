package wooteco.review.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
	private String diffHunk;

	@JsonProperty("body")
	private String content;

	private String updatedAt;

	private String htmlUrl;

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

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
}
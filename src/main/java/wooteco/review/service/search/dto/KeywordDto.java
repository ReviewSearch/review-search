package wooteco.review.service.search.dto;

import javax.validation.constraints.NotNull;

import wooteco.review.domain.Keyword;

public class KeywordDto {
	@NotNull
	private String keyword;
	@NotNull
	private String repoName;

	public KeywordDto(final String keyword, final String repoName) {
		this.keyword = keyword;
		this.repoName = repoName;
	}

	public Keyword toKeyword() {
		return new Keyword(keyword, repoName);
	}

	public String getKeyword() {
		return keyword;
	}
}

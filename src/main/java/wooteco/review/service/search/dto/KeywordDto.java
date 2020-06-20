package wooteco.review.service.search.dto;

import javax.validation.constraints.NotNull;

import wooteco.review.domain.Keyword;

public class KeywordDto {
	@NotNull
	private String keyword;
	@NotNull
	private String RepoName;

	public KeywordDto(final String keyword, final String repoName) {
		this.keyword = keyword;
		RepoName = repoName;
	}

	public Keyword toKeyword() {
		return new Keyword(keyword);
	}

	public String getKeyword() {
		return keyword;
	}
}

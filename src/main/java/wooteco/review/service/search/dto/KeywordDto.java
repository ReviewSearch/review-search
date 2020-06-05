package wooteco.review.service.search.dto;

import javax.validation.constraints.NotNull;

import wooteco.review.domain.Keyword;

public class KeywordDto {
	@NotNull
	private String keyword;

	public KeywordDto(String keyword) {
		this.keyword = keyword;
	}

	public Keyword toKeyword() {
		return new Keyword(keyword);
	}

	public String getKeyword() {
		return keyword;
	}
}

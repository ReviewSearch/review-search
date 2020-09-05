package wooteco.review.domain;

public class Keyword {
	private final String keyword;
	private final String repoName;

	public Keyword(String keyword, String repoName) {
		this.keyword = keyword;
		this.repoName = repoName;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getRepoName() {
		return repoName;
	}
}

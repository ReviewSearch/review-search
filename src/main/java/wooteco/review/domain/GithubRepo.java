package wooteco.review.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("REPOSITORY")
public class GithubRepo {
	@Id
	private final Long id;
	private final String name;
	private final Set<PullRequest> pullRequests;

	GithubRepo(final Long id, final String name, final Set<PullRequest> pullRequests) {
		this.id = id;
		this.name = name;
		this.pullRequests = pullRequests;
	}

	public static GithubRepo of(final String name) {
		return new GithubRepo(null, name, new HashSet<>());
	}

	public GithubRepo withId(final Long id) {
		return new GithubRepo(id, this.name, this.pullRequests);
	}

	public GithubRepo withPullRequests(final Set<PullRequest> pullRequests) {
		return new GithubRepo(this.id, this.name, pullRequests);
	}

	public Long getId() {
		return id;
	}

	public Set<PullRequest> getPullRequests() {
		return pullRequests;
	}


}

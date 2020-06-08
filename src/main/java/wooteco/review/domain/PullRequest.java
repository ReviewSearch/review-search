package wooteco.review.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import wooteco.review.service.github.State;

@Table("PULL_REQUEST")
public class PullRequest {
	@Id
	private final Long id;
	private final Long number;
	private final LocalDateTime updatedAt;
	private final State state;
	private final Set<Comment> comments;

	PullRequest(Long id, final Long number, final LocalDateTime updatedAt, State state,
		final Set<Comment> comments) {
		this.id = id;
		this.number = number;
		this.updatedAt = updatedAt;
		this.state = state;
		this.comments = comments;
	}

	public static PullRequest of(final Long id, final Long number, State state, final LocalDateTime updatedAt) {
		return new PullRequest(id, number, updatedAt, state, new HashSet<>());
	}

	public PullRequest withId(final Long id) {
		return new PullRequest(id, this.number, this.updatedAt, this.state, this.comments);
	}

	public PullRequest withNumber(final Long number) {
		return new PullRequest(id, number, this.updatedAt, state, this.comments);
	}

	public PullRequest withComments(final Set<Comment> comments) {
		return new PullRequest(id, this.number, this.updatedAt, state, comments);
	}
}
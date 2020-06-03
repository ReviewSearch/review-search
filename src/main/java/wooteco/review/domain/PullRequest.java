package wooteco.review.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import wooteco.review.service.github.State;

public class PullRequest {
    @Id
	private final Long number;
    private final LocalDateTime updatedAt;
	private final State state;
	@Column("PULL_REQUEST")
	private final Set<Comment> comments;

	PullRequest(final Long number, final LocalDateTime updatedAt, State state,
		final Set<Comment> comments) {
		this.number = number;
        this.updatedAt = updatedAt;
		this.state = state;
		this.comments = comments;
    }

	public static PullRequest of(final Long number, State state, final LocalDateTime updatedAt) {
		return new PullRequest(number, updatedAt, state, new HashSet<>());
	}

	public PullRequest withNumber(final Long number) {
		return new PullRequest(number, this.updatedAt, state, this.comments);
	}

	public PullRequest withComments(final Set<Comment> comments) {
		return new PullRequest(this.number, this.updatedAt, state, comments);
    }
}
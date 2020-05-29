package wooteco.review.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class PullRequest {
    @Id
	private final Long number;
    private final LocalDateTime updatedAt;
	@Column("PULL_REQUEST")
	private final Set<Comment> comments;

	PullRequest(final Long number, final LocalDateTime updatedAt, final Set<Comment> comments) {
		this.number = number;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

	public static PullRequest of(final Long number, final LocalDateTime updatedAt) {
		return new PullRequest(number, updatedAt, new HashSet<>());
	}

	public PullRequest withNumber(final Long number) {
		return new PullRequest(number, this.updatedAt, this.comments);
	}

	public PullRequest withComments(final Set<Comment> comments) {
		return new PullRequest(this.number, this.updatedAt, comments);
    }
}

package wooteco.review.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

public class PullRequest {
    @Id
    private final Long id;
    private final LocalDateTime updatedAt;
    private final Set<Comment> comments;

    PullRequest(final Long id, final LocalDateTime updatedAt, final Set<Comment> comments) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public static PullRequest of(Long id, LocalDateTime updatedAt) {
        return new PullRequest(id, updatedAt, new HashSet<>());
    }
}

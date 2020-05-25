package wooteco.review.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Comment {
    @Id
    private final Long id;
    private final String diffHunk;
    private final String content;
    private final LocalDateTime updatedAt;
    private final String htmlUrl;

    Comment(Long id, String diffHunk, String content, LocalDateTime updatedAt, String htmlUrl) {
        this.id = id;
        this.diffHunk = diffHunk;
        this.content = content;
        this.updatedAt = updatedAt;
        this.htmlUrl = htmlUrl;
    }

    public static Comment of(String diffHunk, String content, LocalDateTime updatedAt, String htmlUrl) {
        return new Comment(null, diffHunk, content, updatedAt, htmlUrl);
    }

    public Comment withId(Long id){
        return new Comment(id, this.diffHunk, this.content, this.updatedAt, this.htmlUrl);
    }
}

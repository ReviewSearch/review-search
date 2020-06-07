package wooteco.review.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("COMMENT")
public class Comment {
    @Id
    private final Long id;
    private final String login;
    private final String content;
    private final LocalDateTime updatedAt;
    private final String htmlUrl;

    Comment(final Long id, String login, final String content,
        final LocalDateTime updatedAt, final String htmlUrl) {
        this.id = id;
        this.login = login;
        this.content = content;
        this.updatedAt = updatedAt;
        this.htmlUrl = htmlUrl;
    }

    public static Comment of(final Long id, final String login, final String content,
        final LocalDateTime updatedAt, final String htmlUrl) {
        return new Comment(id, login, content, updatedAt, htmlUrl);
    }

    public Comment withId(final Long id) {
        return new Comment(id, this.login, this.content, this.updatedAt, this.htmlUrl);
    }

    public String getLogin() {
        return login;
    }

    public String getContent() {
        return content;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}

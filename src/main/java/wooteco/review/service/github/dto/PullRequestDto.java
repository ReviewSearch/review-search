package wooteco.review.service.github.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.github.State;

/**
 *    Pull Request 클래스입니다.
 *
 *    @author HyungJu An, Seyun Kim, Jinju Moon
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PullRequestDto {
    @NotNull
    private Long number;

    @JsonProperty("state")
    @NotNull
    private State state;

    @NotNull
    private LocalDateTime updatedAt;

    public PullRequest toPullRequest() {
        return PullRequest.of(number, state, updatedAt);
    }

    public Long getNumber() {
        return number;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public State getState() {
        return state;
    }
}

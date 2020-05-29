package wooteco.review.service.github.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import wooteco.review.domain.PullRequest;

/**
 *    Pull Request 클래스입니다.
 *
 *    @author HyungJu An, Seyun Kim, Jinju Moon
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PullRequestDto {
	@NotNull
	private Long number;

	@NotNull
	private LocalDateTime updatedAt;

	public PullRequest toPullRequest() {
		return PullRequest.of(number, updatedAt);
	}

	public Long getNumber() {
		return number;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}

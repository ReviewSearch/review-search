package wooteco.review.service.github.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("name")
	@NotNull
	private Long id;
	@NotNull
	private LocalDateTime updatedAt;

	public PullRequest of(){
		return PullRequest.of(id, updatedAt);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}

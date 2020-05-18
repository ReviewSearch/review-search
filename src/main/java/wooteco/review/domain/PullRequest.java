package wooteco.review.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 *    Pull Request 클래스입니다.
 *
 *    @author HyungJu An, Seyun Kim, Jinju Moon
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PullRequest {
	@NotNull
	private Integer number;
	@NotNull
	private LocalDateTime updatedAt;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}

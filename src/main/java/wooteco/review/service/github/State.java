package wooteco.review.service.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum State {
	@JsonProperty("all")
	ALL,
	@JsonProperty("open")
	OPEN,
	@JsonProperty("closed")
	CLOSED
}

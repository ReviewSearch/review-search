package wooteco.review.controller;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubControllerTest {
	// @formatter:off

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void createRepo() {
		Map<String, String> params = new HashMap<>();
		params.put("name", "roadmap");

		given()
			.body(params)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when()
			.post("/api/github/repos")
			.then()
			.log().all()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	void updatePullRequest() {
		Map<String, String> params = new HashMap<>();
		params.put("name", "java-blackjack");

		given()
			.body(params)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when()
			.put("/api/github/repos")
			.then()
			.log().all()
			.statusCode(HttpStatus.OK.value());
	}
}
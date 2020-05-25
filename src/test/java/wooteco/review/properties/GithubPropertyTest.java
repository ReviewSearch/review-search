package wooteco.review.properties;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubPropertyTest {
	@Autowired
	GithubProperty githubProperty;

	@Test
	void getClientIdTest() {
		assertThat(githubProperty.getClientId()).isNotNull();
	}

	@Test
	void getClientSecret() {
		assertThat(githubProperty.getClientSecret()).isNotNull();
	}
}
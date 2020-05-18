package wooteco.review.githubapi;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubClientTest {

	@Autowired
	GithubClient githubClient;

	@Test
	void requestCommentBy_ShouldReturnSizeOfComments() {
		assertThat(githubClient.requestCommentsBy("java-blackjack", 11)).hasSize(8);
	}

	@Test
	void requestPullRequestBy_ShouldReturnSizeOfPullRequest() {
		assertThat(githubClient.requestPullRequestsBy("java-blackjack")).hasSize(108);
	}
}
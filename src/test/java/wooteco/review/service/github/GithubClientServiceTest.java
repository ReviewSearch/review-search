package wooteco.review.service.github;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubClientServiceTest {
	@Autowired
	GithubClientService githubClientService;

	@Test
	void requestCommentBy_ShouldReturnSizeOfComments() {
		assertThat(githubClientService.requestCommentsBy("java-blackjack", 11L)).hasSize(8);
	}

	@Test
	void requestPullRequestBy_ShouldReturnSizeOfPullRequest() {
		assertThat(githubClientService.requestPullRequestsBy("java-blackjack")).hasSize(108);
	}
}
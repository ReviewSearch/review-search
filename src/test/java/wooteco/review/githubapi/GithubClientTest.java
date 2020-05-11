package wooteco.review.githubapi;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import wooteco.review.domain.Comment;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubClientTest {

	@Autowired
	GithubClient githubClient;

	@Test
	void listCommentBy_ShouldReturnSizeOfComments() {
		List<Comment> comments = githubClient.listCommentsBy("java-chess", 111);
		for (Comment comment : comments) {
			System.out.println("diffHunk" + comment.getDiffHunk());
			System.out.println("content" + comment.getContent());
			System.out.println("url" + comment.getHtmlUrl());
			System.out.println("updatedAt" + comment.getUpdatedAt());
			System.out.println("-------------");
		}
		assertThat(githubClient.listCommentsBy("java-blackjack", 11)).hasSize(8);
	}
}
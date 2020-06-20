package wooteco.review.service.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import wooteco.review.config.MyJdbcConfiguration;
import wooteco.review.domain.Comment;
import wooteco.review.domain.GithubRepo;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.github.State;

@DataJdbcTest
@Import(MyJdbcConfiguration.class)
class CommentRepositoryTest {
	@Autowired
	private RepoRepository repoRepository;

	@Autowired
	private CommentRepository commentRepository;

	@BeforeEach
	void setUp() {
		GithubRepo githubRepo = GithubRepo.of("todolist");
		PullRequest pullRequest1 = PullRequest.of(1L, 1L, State.CLOSED, LocalDateTime.now());
		Comment comment1 = Comment.of(1L, "pobi", "인터페이스를 활용하세요.", LocalDateTime.now(),
			"https://github.com");
		Comment comment2 = Comment.of(2L, "brown", "인터페이스로 분리해보세요..", LocalDateTime.now(),
			"https://github.com");
		Comment comment3 = Comment.of(3L, "jason", "잘하셨어요 ㅎㅎ", LocalDateTime.now(),
			"https://github.com");

		Set<Comment> comments = new HashSet<>();
		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);
		pullRequest1 = pullRequest1.withComments(comments);
		Set<PullRequest> pullRequests = new HashSet<>();
		pullRequests.add(pullRequest1);

		githubRepo = githubRepo.withPullRequests(pullRequests);
		repoRepository.save(githubRepo);
	}

	@Test
	void findByContentContainingTest() {
		assertThat(commentRepository.findByContentContaining("인터")).hasSize(2);
	}
}
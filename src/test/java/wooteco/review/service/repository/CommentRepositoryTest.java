package wooteco.review.service.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		GithubRepo blackjackRepo = GithubRepo.of("java-blackjack");
		GithubRepo todoListRepo = GithubRepo.of("todolist");
		PullRequest pullRequest1 = PullRequest.of(1L, 1L, State.CLOSED, LocalDateTime.now());
		PullRequest pullRequest2 = PullRequest.of(2L, 2L, State.CLOSED, LocalDateTime.now());
		Comment comment1 = Comment.of(1L, "pobi", "인터페이스를 활용하세요.", LocalDateTime.now(),
			"https://github.com");
		Comment comment2 = Comment.of(2L, "brown", "인터페이스로 분리해보세요..", LocalDateTime.now(),
			"https://github.com");
		Comment comment3 = Comment.of(3L, "jason", "인터페이스!", LocalDateTime.now(),
			"https://github.com");
		Set<Comment> comments = new HashSet<>();
		comments.add(comment1);
		comments.add(comment2);
		pullRequest1 = pullRequest1.withComments(comments);
		Set<PullRequest> pullRequests = new HashSet<>();
		pullRequests.add(pullRequest1);
		blackjackRepo = blackjackRepo.withPullRequests(pullRequests);
		repoRepository.save(blackjackRepo);

		Set<Comment> comments2 = new HashSet<>();
		comments2.add(comment3);
		pullRequest2 = pullRequest2.withComments(comments2);
		pullRequests = new HashSet<>();
		pullRequests.add(pullRequest2);
		todoListRepo = todoListRepo.withPullRequests(pullRequests);
		repoRepository.save(todoListRepo);
	}

	@Test
	void findByContentContainingTest() {
		assertThat(commentRepository.findByContentContaining("인터")).hasSize(3);
	}

	@Test
	void findByContentContainingByRepoTest(){
		assertThat(commentRepository.findByContentContainingByRepoId(1L, "인터")).hasSize(2);
	}
}
package wooteco.review.service.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import wooteco.review.domain.GithubRepo;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.github.State;
import wooteco.review.service.repository.PullRequestRepository;
import wooteco.review.service.repository.RepoRepository;

@DataJdbcTest
public class PullRequestRepositoryTest {
	@Autowired
	private RepoRepository repoRepository;

	@Autowired
	private PullRequestRepository pullRequestRepository;

	@BeforeEach
	public void beforeAll() {
		GithubRepo githubRepo = GithubRepo.of("java-blackjack");
		PullRequest pullRequest1 = PullRequest.of(1L, 1L, State.CLOSED, LocalDateTime.now());
		PullRequest pullRequest2 = PullRequest.of(2L, 1L, State.CLOSED, LocalDateTime.now());
		PullRequest pullRequest3 = PullRequest.of(3L, 1L, State.CLOSED, LocalDateTime.now());
		Set<PullRequest> pullRequests = new HashSet<>();
		pullRequests.add(pullRequest1);
		pullRequests.add(pullRequest2);
		pullRequests.add(pullRequest3);

		githubRepo = githubRepo.withPullRequests(pullRequests);

		repoRepository.save(githubRepo);
	}

	@Test
	public void countByStateTest() {
		assertThat(pullRequestRepository.countByState(State.CLOSED)).isEqualTo(3);
	}
}

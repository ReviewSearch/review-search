package wooteco.review.service.search;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import wooteco.review.domain.Comment;
import wooteco.review.domain.GithubRepo;
import wooteco.review.domain.Keyword;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.github.State;
import wooteco.review.service.repository.RepoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
class SearchServiceTest {
	@Autowired
	private SearchService searchService;

	@Autowired
	private RepoRepository repoRepository;

	// @formatter:off
	@DisplayName("키워드로 검색결과를 반환")
	@Test
	void searchCommentsBy() {
		GithubRepo githubRepo = GithubRepo.of("java");
		PullRequest pullRequest = PullRequest.of(1L, 1L, State.CLOSED, LocalDateTime.now());
		Set<PullRequest> pullRequests = new HashSet<>();
		List<Comment> comments = Arrays.asList(
			Comment.of(1L, "sickal", "오우, 대단하네요11!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-1"),
			Comment.of(2L, "sickal", "오우, 대단하네요22!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-2"),
			Comment.of(3L, "sickal", "오우, 대단하네요33!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-3")
			);
		pullRequest = pullRequest.withComments(comments.stream().collect(Collectors.toSet()));
		pullRequests.add(pullRequest);
		githubRepo = githubRepo.withPullRequests(pullRequests);
		repoRepository.save(githubRepo);
		List<Comment> searchedComments = searchService.searchCommentsBy(new Keyword("오우", "ALL"));
		assertThat(searchedComments.size()).isEqualTo(3);
	}
}
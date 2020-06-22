package wooteco.review.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wooteco.review.domain.Comment;
import wooteco.review.domain.GithubRepo;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.RepoService;
import wooteco.review.service.github.GithubClientService;
import wooteco.review.service.github.State;
import wooteco.review.service.github.dto.CommentDto;
import wooteco.review.service.github.dto.PullRequestDto;

@RequestMapping("/api/github")
@RestController
public class GitHubController {
	private final GithubClientService githubClientService;
	private final RepoService repoService;

	public GitHubController(final GithubClientService githubClientService, final RepoService repoService) {
		this.githubClientService = githubClientService;
		this.repoService = repoService;
	}

	@PostMapping("/repos")
	public ResponseEntity<Void> createRepo(@RequestBody Map<String, String> params) {
		final String name = params.get("name");
		final List<PullRequestDto> pullRequestDtos = githubClientService.requestPullRequestsBy(name, State.ALL);
		final Set<PullRequest> pullRequests = new HashSet<>();

		for (final PullRequestDto pullRequestDto : pullRequestDtos) {
			final PullRequest pullRequest = pullRequestDto.toPullRequest();
			final List<CommentDto> commentDtos = githubClientService.requestCommentsBy(name,
				pullRequestDto.getNumber());
			final Set<Comment> comments = commentDtos.stream()
				.map(CommentDto::toComment)
				.collect(Collectors.toSet());

			pullRequests.add(pullRequest.withComments(comments));
		}

		final GithubRepo githubRepo = GithubRepo.of(name);
		final GithubRepo persistenceRepo = repoService.createRepo(githubRepo.withPullRequests(pullRequests));

		return ResponseEntity
			.created(URI.create("/repos/" + persistenceRepo.getId()))
			.build();
	}

	@PutMapping("/repos")
	public ResponseEntity<Void> updatePullRequest(@RequestBody Map<String, String> params) {
		final String name = params.get("name");
		githubClientService.updatePullRequest(name);
		return ResponseEntity
			.ok()
			.build();
	}
}
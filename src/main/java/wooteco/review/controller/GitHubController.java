package wooteco.review.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wooteco.review.domain.Comment;
import wooteco.review.domain.GithubRepo;
import wooteco.review.domain.PullRequest;
import wooteco.review.service.RepoService;
import wooteco.review.service.github.GithubClientService;
import wooteco.review.service.github.dto.CommentDto;
import wooteco.review.service.github.dto.PullRequestDto;

@RequestMapping("/api/github")
@RestController
public class GitHubController {
    private final GithubClientService githubClientService;
    private final RepoService repoService;

    public GitHubController(GithubClientService githubClientService, RepoService repoService) {
        this.githubClientService = githubClientService;
        this.repoService = repoService;
    }

    @PostMapping("/repos")
    public ResponseEntity<Void> createRepo(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        List<PullRequestDto> pullRequestDtos = githubClientService.requestPullRequestsBy(name);
        Set<PullRequest> pullRequests = new HashSet<>();

        for (PullRequestDto pullRequestDto : pullRequestDtos) {
            PullRequest pullRequest = pullRequestDto.toPullRequest();

            List<CommentDto> commentDtos = githubClientService.requestCommentsBy(name, pullRequestDto.getNumber());
            Set<Comment> comments = commentDtos.stream()
                .map(CommentDto::toComment)
                .collect(Collectors.toSet());

            pullRequests.add(pullRequest.withComments(comments));
        }

        GithubRepo githubRepo = GithubRepo.of(name);

        repoService.createRepo(githubRepo.withPullRequests(pullRequests));

        return ResponseEntity
            .created(URI.create("/repos")) // TODO: 2020-05-25 id 추가하기
            .build();
    }
}

package wooteco.review.controller;

import org.springframework.web.bind.annotation.RestController;

import wooteco.review.service.github.GithubClientService;

@RestController
public class GitHubController {
    private final GithubClientService githubClientService;

    public GitHubController(GithubClientService githubClientService) {
        this.githubClientService = githubClientService;
    }
}

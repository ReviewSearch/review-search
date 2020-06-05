package wooteco.review.service;

import org.springframework.stereotype.Service;

import wooteco.review.domain.GithubRepo;
import wooteco.review.service.repository.RepoRepository;

@Service
public class RepoService {
	private final RepoRepository repoRepository;

	public RepoService(final RepoRepository repoRepository) {
		this.repoRepository = repoRepository;
	}

	public GithubRepo createRepo(GithubRepo githubRepo) {
		return repoRepository.save(githubRepo);
	}
}

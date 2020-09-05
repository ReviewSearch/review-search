package wooteco.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wooteco.review.domain.GithubRepo;
import wooteco.review.service.repository.RepoRepository;

@Service
public class RepoService {
	private final RepoRepository repoRepository;

	public RepoService(final RepoRepository repoRepository) {
		this.repoRepository = repoRepository;
	}

	@Transactional
	public GithubRepo createRepo(final GithubRepo githubRepo) {
		return repoRepository.save(githubRepo);
	}

	@Transactional(readOnly = true)
	public List<String> findAllName() {
		return repoRepository.findAllName();
	}
}

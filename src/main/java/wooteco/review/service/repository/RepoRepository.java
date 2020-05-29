package wooteco.review.service.repository;

import org.springframework.data.repository.CrudRepository;

import wooteco.review.domain.GithubRepo;

public interface RepoRepository extends CrudRepository<GithubRepo, Long> {
}

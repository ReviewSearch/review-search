package wooteco.review.service.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import wooteco.review.domain.GithubRepo;

public interface RepoRepository extends CrudRepository<GithubRepo, Long> {
	@Query("SELECT name FROM REPOSITORY")
	List<String> findAllName();

	@Query("SELECT id FROM REPOSITORY WHERE name = :repoName")
	Long findIdByName(@Param("repoName") String repoName);
}

package wooteco.review.service.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.ResultSetExtractor;

import wooteco.review.domain.GithubRepo;

public interface RepoRepository extends CrudRepository<GithubRepo, Long> {
	@Query("SELECT name FROM REPOSITORY")
	List<String> findAllName();
}

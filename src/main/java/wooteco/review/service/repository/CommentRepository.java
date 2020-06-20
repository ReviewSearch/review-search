package wooteco.review.service.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import wooteco.review.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByContentContaining(String keyword);

	@Query("SELECT a.login, a.content, a.html_url "
		+ "FROM COMMENT a "
		+ "JOIN PULL_REQUEST b ON a.pull_request = b.id "
		+ "JOIN REPOSITORY c ON b.github_repo = c.id "
		+ "WHERE "
		+ "c.id = :repoId AND "
		+ "a.content LIKE '%'||:keyword||'%'")
	List<Comment> findByContentContainingByRepoId(@Param("repoId") Long RepoId, @Param("keyword") String keyword);
}

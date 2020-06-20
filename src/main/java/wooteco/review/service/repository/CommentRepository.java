package wooteco.review.service.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import wooteco.review.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findByContentContaining(String keyword);

	@Query("SELECT COMMENT.id, COMMENT.login, COMMENT.content, COMMENT.html_url "
		+ "FROM COMMENT, PULL_REQUEST, REPOSITORY"
		+ "WHERE "
		+ "REPOSITORY.id=:repoId AND"
		+ "PULL_REQUEST.id = COMMENT.pull_request AND"
		+ "github_repo = REPOSITORY.id AND"
		+ "content LIKE :keyword")
	List<Comment> findByContentContainingByRepo(@Param("repoId") Long RepoId, @Param("keyword") String keyword);
}

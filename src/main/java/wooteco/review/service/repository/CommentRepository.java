package wooteco.review.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import wooteco.review.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	// @Query("select * from comment where content like '%:keyword%'")
	List<Comment> findByContentContaining(String keyword);
}

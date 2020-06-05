package wooteco.review.service.search;

import java.util.List;

import org.springframework.stereotype.Service;

import wooteco.review.domain.Comment;
import wooteco.review.domain.Keyword;
import wooteco.review.service.repository.CommentRepository;

@Service
public class SearchService {
	private final CommentRepository commentRepository;

	public SearchService(final CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public List<Comment> searchCommentsBy(final Keyword keyword) {
		return commentRepository.findByContentContaining(keyword.getKeyword());
	}
}

package wooteco.review.service.search;

import java.util.List;

import org.springframework.stereotype.Service;

import wooteco.review.domain.Comment;
import wooteco.review.domain.Keyword;
import wooteco.review.service.repository.CommentRepository;
import wooteco.review.service.repository.RepoRepository;

@Service
public class SearchService {
	private final CommentRepository commentRepository;
	private final RepoRepository repoRepository;

	public SearchService(CommentRepository commentRepository,
		RepoRepository repoRepository) {
		this.commentRepository = commentRepository;
		this.repoRepository = repoRepository;
	}

	public List<Comment> searchCommentsBy(final Keyword keyword) {
		if (keyword.getRepoName().equals("ALL")) {
			System.out.println("???");
			return commentRepository.findByContentContaining(keyword.getKeyword());
		}
		Long repoId = repoRepository.findIdByName(keyword.getRepoName());
		return commentRepository.findByContentContainingByRepoId(repoId, keyword.getKeyword());
	}
}

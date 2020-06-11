package wooteco.review.service.search;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import wooteco.review.domain.Comment;
import wooteco.review.domain.Keyword;
import wooteco.review.service.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchServiceTest {
	// @formatter:off
	private SearchService searchService;

	@Mock
	@Autowired
	private CommentRepository commentRepository;

	@BeforeEach
	void setUp() {
		searchService = new SearchService(commentRepository);
	}

	@DisplayName("키워드로 검색결과를 반환")
	@Test
	void searchCommentsBy() {
		List<Comment> comments = Arrays.asList(
			Comment.of(1L, "sickal", "오우, 대단하네요11!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-1"),
			Comment.of(2L, "sickal", "오우, 대단하네요22!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-2"),
			Comment.of(3L, "sickal", "오우, 대단하네요33!", LocalDateTime.now(), "https://github.com/octocat/Hello-World/pull/1#discussion-diff-3")
			);
		when(commentRepository.findByContentContaining(anyString())).thenReturn(comments);

		List<Comment> searchedComments = searchService.searchCommentsBy(new Keyword("오우"));
		assertThat(searchedComments.size()).isEqualTo(3);
	}
	// @formatter:on
}
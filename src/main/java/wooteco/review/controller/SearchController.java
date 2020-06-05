package wooteco.review.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import wooteco.review.service.search.SearchService;
import wooteco.review.service.search.dto.KeywordDto;
import wooteco.review.service.search.dto.SearchResponse;

@RestController
public class SearchController {
	private final SearchService searchService;

	public SearchController(final SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping("/api/comments")
	public ResponseEntity<List<SearchResponse>> searchComments(@Valid KeywordDto keywordDto) {
		List<SearchResponse> searchResponses = SearchResponse.listOf(searchService.searchCommentsBy(keywordDto.toKeyword()));
		return ResponseEntity
			.ok()
			.body(searchResponses);
	}
}

package wooteco.review.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wooteco.review.service.RepoService;
import wooteco.review.service.repository.RepoRepository;

@Controller
public class PageController {
	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String index() {
		return "index";
	}
}

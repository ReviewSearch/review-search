package wooteco.review.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import wooteco.review.domain.GithubRepo;
import wooteco.review.service.repository.RepoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
class RepoServiceTest {
	@Autowired
	private RepoService repoService;

	@Autowired
	private RepoRepository repoRepository;

	@BeforeEach
	public void setup() {
		List<GithubRepo> githubRepos = Arrays.asList(
			GithubRepo.of("java-blackjack"),
			GithubRepo.of("java-calculator"),
			GithubRepo.of("java-racing-car")
		);
		repoRepository.saveAll(githubRepos);
	}

	@Test
	void findAllNameTest() {
		List<String> repoNames = repoService.findAllName();
		assertThat(repoNames).hasSize(3);
	}

	@Test
	void createRepoTest() {
		GithubRepo todoList = repoService.createRepo(GithubRepo.of("todolist"));
		assertThat(todoList.getId()).isNotNull();
	}
}
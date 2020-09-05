package wooteco.review.service.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import wooteco.review.domain.GithubRepo;

@DataJdbcTest
class RepoRepositoryTest {
	@Autowired
	private RepoRepository repoRepository;

	@Test
	void findAllName() {
		repoRepository.save(GithubRepo.of("java-blackjack"));
		repoRepository.save(GithubRepo.of("java-chess"));

		assertThat(repoRepository.findAllName()).hasSize(2);
	}
}
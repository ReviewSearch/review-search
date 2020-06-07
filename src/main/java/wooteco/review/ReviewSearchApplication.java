package wooteco.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import wooteco.review.properties.GithubProperty;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperty.class)
public class ReviewSearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReviewSearchApplication.class, args);
	}
}

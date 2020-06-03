package wooteco.review.service.repository;

import org.springframework.data.repository.CrudRepository;

import wooteco.review.domain.PullRequest;
import wooteco.review.service.github.State;

public interface PullRequestRepository extends CrudRepository<PullRequest, Long> {
    long countByState(State state);
}

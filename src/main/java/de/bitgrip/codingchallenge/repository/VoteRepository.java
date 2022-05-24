package de.bitgrip.codingchallenge.repository;

import de.bitgrip.codingchallenge.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
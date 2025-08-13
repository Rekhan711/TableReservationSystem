package com.pollsystem.service;

import com.pollsystem.entity.Vote;
import java.util.List;
import java.util.Optional;

public interface VoteService {
    Vote saveVote(Vote vote);
    Optional<Vote> findById(Long id);
    List<Vote> findAll();
}

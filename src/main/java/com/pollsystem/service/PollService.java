package com.pollsystem.service;

import com.pollsystem.entity.Poll;
import java.util.List;
import java.util.Optional;

public interface PollService {
    Poll savePoll(Poll poll);
    Optional<Poll> findById(Long id);
    List<Poll> findAll();
    void deleteById(Long id);
}

package com.reservation.service;

import com.reservation.entity.Poll;
import java.util.List;
import java.util.Optional;

public interface PollService {
    Poll savePoll(Poll poll);
    Optional<Poll> findById(Long id);
    List<Poll> findAll();
    void deleteById(Long id);
}

package com.reservation.service;

import com.reservation.entity.Poll;
import com.reservation.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    @Override
    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public Optional<Poll> findById(Long id) {
        return pollRepository.findById(id);
    }

    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        pollRepository.deleteById(id);
    }
}

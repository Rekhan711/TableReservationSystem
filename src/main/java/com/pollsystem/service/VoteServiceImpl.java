package com.pollsystem.service;

import com.pollsystem.entity.Option;
import com.pollsystem.entity.Vote;
import com.pollsystem.repository.OptionRepository;
import com.pollsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final OptionRepository optionRepository; // нужен для поиска варианта

    @Override
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public Optional<Vote> findById(Long id) {
        return voteRepository.findById(id);
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote castVote(Long optionId) {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Option not found with id " + optionId));

        Vote vote = new Vote();
        vote.setOption(option);

        return voteRepository.save(vote);
    }
}

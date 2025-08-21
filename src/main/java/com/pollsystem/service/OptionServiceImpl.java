package com.pollsystem.service;

import com.pollsystem.entity.Option;
import com.pollsystem.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public void saveOption(Option option) {
        optionRepository.save(option);
    }

    @Override
    public Optional<Option> findById(Long id) {
        return optionRepository.findById(id);
    }

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        optionRepository.deleteById(id);
    }
}

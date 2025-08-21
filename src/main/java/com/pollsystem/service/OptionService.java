package com.pollsystem.service;

import com.pollsystem.entity.Option;
import java.util.List;
import java.util.Optional;

public interface OptionService {
    void saveOption(Option option);
    Optional<Option> findById(Long id);
    List<Option> findAll();
    void deleteById(Long id);
}

package com.reservation.service;

import com.reservation.entity.Option;
import java.util.List;
import java.util.Optional;

public interface OptionService {
    Option saveOption(Option option);
    Optional<Option> findById(Long id);
    List<Option> findAll();
    void deleteById(Long id);
}

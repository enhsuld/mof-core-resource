package com.macro.dev.services;

import com.macro.dev.models.TmCodeCombination;

import java.util.List;
import java.util.Optional;

public interface TmCodeCombinationService {

    Optional<TmCodeCombination> getById(String id);

    List<TmCodeCombination> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmCodeCombination object);

    boolean modify(TmCodeCombination object);

    boolean deleteById(String id);

}
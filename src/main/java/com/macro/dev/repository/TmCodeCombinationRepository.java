package com.macro.dev.repository;

import com.macro.dev.models.TmCodeCombination;

import java.util.List;

public interface TmCodeCombinationRepository {

    TmCodeCombination findById(String id);

    List<TmCodeCombination> selectAll();

    List<TmCodeCombination> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmCodeCombination object);

    Integer deleteById(String id);

    int update(TmCodeCombination object);
}
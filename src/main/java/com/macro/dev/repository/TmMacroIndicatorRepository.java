package com.macro.dev.repository;

import com.macro.dev.models.TmMacroIndicator;

import java.util.List;

public interface TmMacroIndicatorRepository {

    TmMacroIndicator findById(String id);

    List<TmMacroIndicator> selectAll();

    List<TmMacroIndicator> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmMacroIndicator object);

    Integer deleteById(String id);

    int update(TmMacroIndicator object);
}
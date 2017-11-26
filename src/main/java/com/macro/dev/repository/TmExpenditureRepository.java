package com.macro.dev.repository;

import com.macro.dev.models.TmExpenditure;

import java.util.List;

public interface TmExpenditureRepository {

    TmExpenditure findById(String id);

    List<TmExpenditure> selectAll();

    List<TmExpenditure> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmExpenditure object);

    Integer deleteById(String id);

    int update(TmExpenditure object);
}

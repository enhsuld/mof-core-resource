package com.macro.dev.repository;

import com.macro.dev.models.TmExpenditureProcess;

import java.util.List;

public interface TmExpenditureProcessRepository {

    TmExpenditureProcess findById(String id);

    List<TmExpenditureProcess> selectAll();

    List<TmExpenditureProcess> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmExpenditureProcess object);

    Integer deleteById(String id);

    int update(TmExpenditureProcess object);
}
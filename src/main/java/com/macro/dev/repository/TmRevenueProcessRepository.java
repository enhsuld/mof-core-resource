package com.macro.dev.repository;

import com.macro.dev.models.TmRevenueProcess;

import java.util.List;

public interface TmRevenueProcessRepository {

    TmRevenueProcess findById(String id);

    List<TmRevenueProcess> selectAll();

    List<TmRevenueProcess> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmRevenueProcess object);

    Integer deleteById(String id);

    int update(TmRevenueProcess object);
}
package com.macro.dev.repository;

import com.macro.dev.models.TmRevenueConfig;

import java.util.List;

public interface TmRevenueConfigRepository {

    TmRevenueConfig findById(String id);

    List<TmRevenueConfig> selectAll();

    List<TmRevenueConfig> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmRevenueConfig object);

    Integer deleteById(String id);

    int update(TmRevenueConfig object);
}
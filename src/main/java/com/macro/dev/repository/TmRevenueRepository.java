package com.macro.dev.repository;

import com.macro.dev.models.TmRevenue;

import java.util.List;

public interface TmRevenueRepository {

    TmRevenue findById(String id);

    List<TmRevenue> selectAll();

    List<TmRevenue> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmRevenue object);

    Integer deleteById(String id);

    int update(TmRevenue object);
}
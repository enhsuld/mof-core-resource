package com.macro.dev.repository;

import com.macro.dev.models.TmRevenueBaseImformation;

import java.util.List;

public interface TmRevenueBaseImformationRepository {

    TmRevenueBaseImformation findById(String id);

    List<TmRevenueBaseImformation> selectAll();

    List<TmRevenueBaseImformation> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmRevenueBaseImformation object);

    Integer deleteById(String id);

    int update(TmRevenueBaseImformation object);
}
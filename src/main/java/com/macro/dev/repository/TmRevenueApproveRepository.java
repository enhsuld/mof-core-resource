package com.macro.dev.repository;

import com.macro.dev.models.TmRevenueApprove;

import java.util.List;

public interface TmRevenueApproveRepository {

    TmRevenueApprove findById(String id);

    List<TmRevenueApprove> selectAll();

    List<TmRevenueApprove> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmRevenueApprove object);

    Integer deleteById(String id);

    int update(TmRevenueApprove object);
}
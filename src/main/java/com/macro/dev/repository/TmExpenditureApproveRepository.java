package com.macro.dev.repository;

import com.macro.dev.models.TmExpenditureApprove;

import java.util.List;

public interface TmExpenditureApproveRepository {

    TmExpenditureApprove findById(String id);

    List<TmExpenditureApprove> selectAll();

    List<TmExpenditureApprove> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmExpenditureApprove object);

    Integer deleteById(String id);

    int update(TmExpenditureApprove object);
}
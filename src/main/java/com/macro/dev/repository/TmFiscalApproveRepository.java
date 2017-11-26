package com.macro.dev.repository;

import com.macro.dev.models.TmFiscalApprove;

import java.util.List;

public interface TmFiscalApproveRepository {

    TmFiscalApprove findById(String id);

    List<TmFiscalApprove> selectAll();

    List<TmFiscalApprove> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmFiscalApprove object);

    Integer deleteById(String id);

    int update(TmFiscalApprove object);
}
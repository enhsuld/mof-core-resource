package com.macro.dev.repository;

import com.macro.dev.models.TmDebt;

import java.util.List;

public interface TmDebtRepository {

    TmDebt findById(String id);

    List<TmDebt> selectAll();

    List<TmDebt> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmDebt object);

    Integer deleteById(String id);

    int update(TmDebt object);
}
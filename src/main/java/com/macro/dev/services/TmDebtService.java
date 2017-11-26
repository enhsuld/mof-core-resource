package com.macro.dev.services;

import com.macro.dev.models.TmDebt;

import java.util.List;
import java.util.Optional;

public interface TmDebtService {

    Optional<TmDebt> getById(String id);

    List<TmDebt> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmDebt object);

    boolean modify(TmDebt object);

    boolean deleteById(String id);

}
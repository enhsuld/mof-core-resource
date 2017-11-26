package com.macro.dev.repository;

import com.macro.dev.models.TmMacroEconomic;

import java.util.List;

public interface TmMacroEconomicRepository {

    TmMacroEconomic findById(String id);

    List<TmMacroEconomic> selectAll();

    List<TmMacroEconomic> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmMacroEconomic object);

    Integer deleteById(String id);

    int update(TmMacroEconomic object);
}
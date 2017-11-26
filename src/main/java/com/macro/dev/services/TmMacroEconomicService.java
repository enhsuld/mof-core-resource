package com.macro.dev.services;

import com.macro.dev.models.TmMacroEconomic;

import java.util.List;
import java.util.Optional;

public interface TmMacroEconomicService {

    Optional<TmMacroEconomic> getById(String id);

    List<TmMacroEconomic> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmMacroEconomic object);

    boolean modify(TmMacroEconomic object);

    boolean deleteById(String id);

}
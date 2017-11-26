package com.macro.dev.services;

import com.macro.dev.models.TmRevenueConfig;

import java.util.List;
import java.util.Optional;

public interface TmRevenueConfigService {

    Optional<TmRevenueConfig> getById(String id);

    List<TmRevenueConfig> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmRevenueConfig object);

    boolean modify(TmRevenueConfig object);

    boolean deleteById(String id);

}
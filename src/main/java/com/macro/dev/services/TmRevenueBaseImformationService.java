package com.macro.dev.services;

import com.macro.dev.models.TmRevenueBaseImformation;

import java.util.List;
import java.util.Optional;

public interface TmRevenueBaseImformationService {

    Optional<TmRevenueBaseImformation> getById(String id);

    List<TmRevenueBaseImformation> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmRevenueBaseImformation object);

    boolean modify(TmRevenueBaseImformation object);

    boolean deleteById(String id);

}
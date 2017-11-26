package com.macro.dev.services;

import com.macro.dev.models.TmRevenue;

import java.util.List;
import java.util.Optional;

public interface TmRevenueService {

    Optional<TmRevenue> getById(String id);

    List<TmRevenue> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmRevenue object);

    boolean modify(TmRevenue object);

    boolean deleteById(String id);

}
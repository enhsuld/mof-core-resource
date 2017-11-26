package com.macro.dev.services;

import com.macro.dev.models.TmRevenueProcess;

import java.util.List;
import java.util.Optional;

public interface TmRevenueProcessService {

    Optional<TmRevenueProcess> getById(String id);

    List<TmRevenueProcess> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmRevenueProcess object);

    boolean modify(TmRevenueProcess object);

    boolean deleteById(String id);

}
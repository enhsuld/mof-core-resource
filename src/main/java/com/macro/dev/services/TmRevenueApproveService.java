package com.macro.dev.services;

import com.macro.dev.models.TmRevenueApprove;

import java.util.List;
import java.util.Optional;

public interface TmRevenueApproveService {

    Optional<TmRevenueApprove> getById(String id);

    List<TmRevenueApprove> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmRevenueApprove object);

    boolean modify(TmRevenueApprove object);

    boolean deleteById(String id);

}
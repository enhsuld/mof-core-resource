package com.macro.dev.services;

import com.macro.dev.models.TmExpenditureApprove;

import java.util.List;
import java.util.Optional;

public interface TmExpenditureApproveService {

    Optional<TmExpenditureApprove> getById(String id);

    List<TmExpenditureApprove> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmExpenditureApprove object);

    boolean modify(TmExpenditureApprove object);

    boolean deleteById(String id);

}
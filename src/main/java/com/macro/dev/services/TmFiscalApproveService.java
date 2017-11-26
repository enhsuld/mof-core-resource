package com.macro.dev.services;

import com.macro.dev.models.TmFiscalApprove;

import java.util.List;
import java.util.Optional;

public interface TmFiscalApproveService {

    Optional<TmFiscalApprove> getById(String id);

    List<TmFiscalApprove> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmFiscalApprove object);

    boolean modify(TmFiscalApprove object);

    boolean deleteById(String id);

}
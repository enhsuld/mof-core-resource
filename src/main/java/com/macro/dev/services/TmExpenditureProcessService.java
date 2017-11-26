package com.macro.dev.services;

import com.macro.dev.models.TmExpenditureProcess;

import java.util.List;
import java.util.Optional;

public interface TmExpenditureProcessService {

    Optional<TmExpenditureProcess> getById(String id);

    List<TmExpenditureProcess> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmExpenditureProcess object);

    boolean modify(TmExpenditureProcess object);

    boolean deleteById(String id);

}
package com.macro.dev.services;

import com.macro.dev.models.TmExpenditure;

import java.util.List;
import java.util.Optional;

public interface TmExpenditureService {

    Optional<TmExpenditure> getById(String id);

    List<TmExpenditure> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmExpenditure object);

    boolean modify(TmExpenditure object);

    boolean deleteById(String id);

}
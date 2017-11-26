package com.macro.dev.services;

import com.macro.dev.models.TmMacroIndicator;

import java.util.List;
import java.util.Optional;

public interface TmMacroIndicatorService {

    Optional<TmMacroIndicator> getById(String id);

    List<TmMacroIndicator> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmMacroIndicator object);

    boolean modify(TmMacroIndicator object);

    boolean deleteById(String id);

}
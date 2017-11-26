package com.macro.dev.services;

import com.macro.dev.models.TmMtef;

import java.util.List;
import java.util.Optional;

public interface TmMtefService {

    Optional<TmMtef> getById(String id);

    List<TmMtef> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TmMtef object);

    boolean modify(TmMtef object);

    boolean deleteById(String id);

}
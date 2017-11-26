package com.macro.dev.repository;

import com.macro.dev.models.TmMtef;

import java.util.List;

public interface TmMtefRepository {

    TmMtef findById(String id);

    List<TmMtef> selectAll();

    List<TmMtef> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TmMtef object);

    Integer deleteById(String id);

    int update(TmMtef object);
}
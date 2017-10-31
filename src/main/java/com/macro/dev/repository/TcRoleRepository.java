package com.macro.dev.repository;

import com.macro.dev.models.TcRole;

import java.util.List;

public interface TcRoleRepository {

    TcRole findById(String id);

    List<TcRole> selectAllTcFaqs();

    List<TcRole> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcRole book);

    Integer deleteById(String id);

    int update(TcRole tcFaq);
}

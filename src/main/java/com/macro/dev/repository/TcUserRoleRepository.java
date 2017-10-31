package com.macro.dev.repository;

import com.macro.dev.models.TcUserRole;

import java.util.List;

public interface TcUserRoleRepository {

    TcUserRole findById(String id);

    List<TcUserRole> selectAllTcFaqs();

    List<TcUserRole> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcUserRole book);

    Integer deleteById(String id);

    int update(TcUserRole tcFaq);
}

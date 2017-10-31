package com.macro.dev.repository;

import com.macro.dev.models.TcRolePgm;

import java.util.List;

public interface TcRolePgmRepository {

    TcRolePgm findById(String id);

    List<TcRolePgm> selectAllTcFaqs();

    List<TcRolePgm> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcRolePgm book);

    Integer deleteById(String id);

    int update(TcRolePgm tcFaq);
}

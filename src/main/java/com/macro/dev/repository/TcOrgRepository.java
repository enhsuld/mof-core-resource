package com.macro.dev.repository;

import com.macro.dev.models.TcOrg;

import java.util.List;

public interface TcOrgRepository {

    TcOrg findById(String id);

    List<TcOrg> selectAllTcFaqs();

    List<TcOrg> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcOrg book);

    Integer deleteById(String id);

    int update(TcOrg tcFaq);
}

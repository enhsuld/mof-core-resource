package com.macro.dev.repository;

import com.macro.dev.models.TcUser;

import java.util.List;

public interface TcUserRepository {

    TcUser findById(String id);

    List<TcUser> selectAllTcFaqs();

    List<TcUser> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcUser book);

    Integer deleteById(String id);

    int update(TcUser tcFaq);
}

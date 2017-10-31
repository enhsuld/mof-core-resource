package com.macro.dev.repository;

import com.macro.dev.models.TcUserLog;

import java.util.List;

public interface TcUserLogRepository {

    TcUserLog findById(String id);

    List<TcUserLog> selectAllTcFaqs();

    List<TcUserLog> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcUserLog book);

    Integer deleteById(String id);

    int update(TcUserLog tcFaq);
}

package com.macro.dev.repository;

import com.macro.dev.models.TcFaq;

import java.util.List;

public interface TcFaqRepository {

    TcFaq findById(String id);

    List<TcFaq> selectAllTcFaqs();

    List<TcFaq> selectFaqByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcFaq book);

    Integer deleteById(String id);

    int update(TcFaq tcFaq);
}

package com.macro.dev.repository;

import com.macro.dev.models.TcExchange;

import java.util.List;

public interface TcExchangeRepository {

    TcExchange findById(String id);

    List<TcExchange> selectAllTcFaqs();

    List<TcExchange> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcExchange book);

    Integer deleteById(String id);

    int update(TcExchange tcFaq);
}

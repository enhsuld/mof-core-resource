package com.macro.dev.services;

import com.macro.dev.models.TcExchange;

import java.util.List;
import java.util.Optional;


public interface TcExchangeService {

    Optional<TcExchange> getById(String id);

    List<TcExchange> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcExchange tcFaq);

    boolean modify(TcExchange tcFaq);

    boolean deleteById(String id);

}

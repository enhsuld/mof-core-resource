package com.macro.dev.repository;

import com.macro.dev.models.TcComCd;

import java.util.List;

public interface TcComCdRepository {

    TcComCd findById(String id);

    List<TcComCd> selectAllTcFaqs();

    List<TcComCd> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcComCd book);

    Integer deleteById(String id);

    int update(TcComCd tcFaq);
}

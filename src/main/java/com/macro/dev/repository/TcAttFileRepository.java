package com.macro.dev.repository;

import com.macro.dev.models.TcAttFile;

import java.util.List;

public interface TcAttFileRepository {

    TcAttFile findById(String id);

    List<TcAttFile> selectAllTcFaqs();

    List<TcAttFile> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcAttFile book);

    Integer deleteById(String id);

    int update(TcAttFile tcFaq);
}

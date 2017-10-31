package com.macro.dev.repository;

import com.macro.dev.models.TcQuestion;

import java.util.List;

public interface TcQuestionRepository {

    TcQuestion findById(String id);

    List<TcQuestion> selectAllTcFaqs();

    List<TcQuestion> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcQuestion book);

    Integer deleteById(String id);

    int update(TcQuestion tcFaq);
}

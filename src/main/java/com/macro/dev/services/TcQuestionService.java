package com.macro.dev.services;

import com.macro.dev.models.TcQuestion;

import java.util.List;
import java.util.Optional;


public interface TcQuestionService {

    Optional<TcQuestion> getById(String id);

    List<TcQuestion> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcQuestion tcFaq);

    boolean modify(TcQuestion tcFaq);

    boolean deleteById(String id);

}

package com.macro.dev.services;

import com.macro.dev.models.TcUserLog;

import java.util.List;
import java.util.Optional;


public interface TcUserLogService {

    Optional<TcUserLog> getById(String id);

    List<TcUserLog> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcUserLog tcFaq);

    boolean modify(TcUserLog tcFaq);

    boolean deleteById(String id);

}

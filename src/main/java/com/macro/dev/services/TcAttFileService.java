package com.macro.dev.services;

import com.macro.dev.models.TcAttFile;

import java.util.List;
import java.util.Optional;


public interface TcAttFileService {

    Optional<TcAttFile> getById(String id);

    List<TcAttFile> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcAttFile tcFaq);

    boolean modify(TcAttFile tcFaq);

    boolean deleteById(String id);

}

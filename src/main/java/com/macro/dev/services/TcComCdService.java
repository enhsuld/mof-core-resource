package com.macro.dev.services;

import com.macro.dev.models.TcComCd;

import java.util.List;
import java.util.Optional;


public interface TcComCdService {

    Optional<TcComCd> getById(String id);

    List<TcComCd> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcComCd tcFaq);

    boolean modify(TcComCd tcFaq);

    boolean deleteById(String id);

}

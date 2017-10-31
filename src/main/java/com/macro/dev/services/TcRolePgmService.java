package com.macro.dev.services;

import com.macro.dev.models.TcRolePgm;

import java.util.List;
import java.util.Optional;


public interface TcRolePgmService {

    Optional<TcRolePgm> getById(String id);

    List<TcRolePgm> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcRolePgm tcFaq);

    boolean modify(TcRolePgm tcFaq);

    boolean deleteById(String id);

}

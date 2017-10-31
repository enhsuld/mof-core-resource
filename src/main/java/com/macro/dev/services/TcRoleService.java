package com.macro.dev.services;

import com.macro.dev.models.TcRole;

import java.util.List;
import java.util.Optional;


public interface TcRoleService {

    Optional<TcRole> getById(String id);

    List<TcRole> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcRole tcFaq);

    boolean modify(TcRole tcFaq);

    boolean deleteById(String id);

}

package com.macro.dev.services;

import com.macro.dev.models.TcUserRole;

import java.util.List;
import java.util.Optional;


public interface TcUserRoleService {

    Optional<TcUserRole> getById(String id);

    List<TcUserRole> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcUserRole tcFaq);

    boolean modify(TcUserRole tcFaq);

    boolean deleteById(String id);

}

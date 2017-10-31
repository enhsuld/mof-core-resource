package com.macro.dev.services;

import com.macro.dev.models.TcOrg;

import java.util.List;
import java.util.Optional;


public interface TcOrgService {

    Optional<TcOrg> getById(String id);

    List<TcOrg> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcOrg tcFaq);

    boolean modify(TcOrg tcFaq);

    boolean deleteById(String id);

}

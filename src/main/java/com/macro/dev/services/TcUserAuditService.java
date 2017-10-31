package com.macro.dev.services;

import com.macro.dev.models.TcUserAudit;

import java.util.List;
import java.util.Optional;


public interface TcUserAuditService {

    Optional<TcUserAudit> getById(String id);

    List<TcUserAudit> getByPage(Integer page, Integer perPage, String query, String order);

    Integer getTotalPage(Integer perPage, String query);

    boolean save(TcUserAudit tcFaq);

    boolean modify(TcUserAudit tcFaq);

    boolean deleteById(String id);

}

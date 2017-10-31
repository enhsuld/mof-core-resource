package com.macro.dev.repository;

import com.macro.dev.models.TcUserAudit;

import java.util.List;

public interface TcUserAuditRepository {

    TcUserAudit findById(String id);

    List<TcUserAudit> selectAllTcFaqs();

    List<TcUserAudit> selectByPage(Integer offset, Integer perPage, String query, String order);

    Integer selectCount(String query);

    Integer insert(TcUserAudit book);

    Integer deleteById(String id);

    int update(TcUserAudit tcFaq);
}

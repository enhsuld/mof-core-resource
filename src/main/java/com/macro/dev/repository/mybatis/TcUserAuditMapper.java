package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcUserAudit;
import com.macro.dev.repository.TcUserAuditRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TcUserAuditMapper extends TcUserAuditRepository {

    @Override
    List<TcUserAudit> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcUserLog;
import com.macro.dev.repository.TcUserLogRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TcUserLogMapper extends TcUserLogRepository {

    @Override
    List<TcUserLog> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

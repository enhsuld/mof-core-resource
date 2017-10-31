package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcUserRole;
import com.macro.dev.repository.TcUserRoleRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TcUserRoleMapper extends TcUserRoleRepository {

    @Override
    List<TcUserRole> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

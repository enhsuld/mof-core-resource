package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TmRevenueApprove;
import com.macro.dev.repository.TmRevenueApproveRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmRevenueApproveMapper extends TmRevenueApproveRepository {

    @Override
    List<TmRevenueApprove> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}
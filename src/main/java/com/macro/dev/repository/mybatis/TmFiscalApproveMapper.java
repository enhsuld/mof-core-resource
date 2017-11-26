package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TmFiscalApprove;
import com.macro.dev.repository.TmFiscalApproveRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmFiscalApproveMapper extends TmFiscalApproveRepository {

    @Override
    List<TmFiscalApprove> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}
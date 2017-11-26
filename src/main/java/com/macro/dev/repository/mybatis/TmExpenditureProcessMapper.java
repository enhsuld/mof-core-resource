package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TmExpenditureProcess;
import com.macro.dev.repository.TmExpenditureProcessRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmExpenditureProcessMapper extends TmExpenditureProcessRepository {

    @Override
    List<TmExpenditureProcess> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}
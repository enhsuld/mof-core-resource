package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TmExpenditure;
import com.macro.dev.repository.TmExpenditureRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmExpenditureMapper extends TmExpenditureRepository {

    @Override
    List<TmExpenditure> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}
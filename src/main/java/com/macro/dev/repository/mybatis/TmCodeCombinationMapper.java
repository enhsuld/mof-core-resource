package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TmCodeCombination;
import com.macro.dev.repository.TmCodeCombinationRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmCodeCombinationMapper extends TmCodeCombinationRepository {

    @Override
    List<TmCodeCombination> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}
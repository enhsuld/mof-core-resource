package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcFaq;
import com.macro.dev.repository.TcFaqRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TcFaqMapper extends TcFaqRepository {

    @Override
    List<TcFaq> selectFaqByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage , @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcQuestion;
import com.macro.dev.repository.TcQuestionRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TcQuestionMapper extends TcQuestionRepository {

    @Override
    List<TcQuestion> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

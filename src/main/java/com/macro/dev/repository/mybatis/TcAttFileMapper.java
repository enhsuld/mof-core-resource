package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcAttFile;
import com.macro.dev.repository.TcAttFileRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TcAttFileMapper extends TcAttFileRepository {

    @Override
    List<TcAttFile> selectByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage, @Param("query") String query, @Param("order") String order);

    @Override
    Integer selectCount(@Param("query") String query);

}

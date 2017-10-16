package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcPgm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TcPgmMapper {

    @Select("select pgm_id,pgm_nm,call_url,ui_icon,parent_id,update_type from tc_pgm where parent_id is null")
    @Results(value = {
            @Result(property="pgm_id", column="pgm_id"),
            @Result(property="pgm_nm", column="pgm_nm"),
            @Result(property="call_url", column="call_url"),
            @Result(property="ui_icon", column="ui_icon"),
            @Result(property="parent_id", column="parent_id"),
            @Result(property="update_type", column="update_type"),
            @Result(property="children", javaType=List.class, column="pgm_id",  many=@Many(select="getChilds"))
    })
    List<TcPgm> findAll();


    @Select("SELECT pgm_id,pgm_nm,call_url,ui_icon,parent_id,update_type FROM tc_pgm WHERE parent_id = #{pgm_id}")
    @Results(value = {
        @Result(property="children", javaType=List.class, column="pgm_id",  many=@Many(select="getChildThird"))
    })
    List<TcPgm> getChilds(String pgm_id);

    @Select("SELECT pgm_id,pgm_nm,call_url,ui_icon,parent_id,update_type FROM tc_pgm WHERE parent_id = #{pgm_id}")
    List<TcPgm> getChildThird(String pgm_id);

    @Insert("insert into users(name,salary) values(#{name},#{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
            before = false, resultType = Integer.class)
    void insert(TcPgm users);
}

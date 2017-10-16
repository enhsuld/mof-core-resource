package com.macro.dev.repository.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

public interface CrudMapper {

	@Select("select * from ${service} where id=${id}")
	public HashMap<String, Object> getObjectById(@Param("service") String service, @Param("id") Integer id);

	@Select("select * from ${service} where ${col}='${val}'")
	public HashMap<String, String> getObjectByPath(@Param("service") String service, @Param("col") String col,@Param("val") String val);

	@Select("${query}")
	@MapKey("section")
	@Results(value = {
			@Result(column = "parent_id", property = "parent_id", typeHandler = EmptyStringTypeHandler.class)
	})
	public List<Map<String, String>>  getListObject(@Param("query") String query);
	
	@Insert("insert into ${table} (${columns}) values (${values})")
	public Integer createCrObject(@Param("table") String table, @Param("columns") String columns, @Param("values") String values);

	@Select("${query}")
	Long getListCount(@Param("query") String query);

	@Insert("insert into ${table} (${key},${columns}) values (${seq}.nextval ,${values})")
	public Integer createObject(@Param("table") String table, @Param("columns") String columns, @Param("values") String values,@Param("key") String key,@Param("seq") String seq);

	@Update("UPDATE ${table} SET ${col} WHERE ${key} =#{id}")
	void updateObject(@Param("table") String table,@Param("col") String col,@Param("key") String key,@Param("id") int id);

	@Delete("DELETE FROM ${table} WHERE ${key} =#{id}")
	void deleteObject(@Param("table") String table,@Param("key") String key,@Param("id") Long id);


}

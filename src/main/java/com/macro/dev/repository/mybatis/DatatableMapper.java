package com.macro.dev.repository.mybatis;

import java.util.HashMap;
import java.util.List;

import com.macro.dev.constant.DatatableInput;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DatatableMapper {

	@Select("select count(*) from ${service} ")
	public Integer count(DatatableInput datatableInput);

	/*@Select("select * from ${service} "
		    + " ${fullsearch} "
		    + " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
			+ " OFFSET #{start} ROWS FETCH NEXT #{length} ROWS ONLY" )*/


	@Select( "select * from ( "
			+ "SELECT b.*, ROWNUM RN  FROM ( select * from ${service} t "
			+ " ${custom} "
			+ " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
			+ " ) b WHERE ROWNUM <= #{length} ) WHERE RN > #{start}")
	public  List<HashMap<String, Object>> getDatatable(DatatableInput datatableInput);
	
	@Select("<script>"
			+ "select * from ${service} "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE ${fullsearch} "
		    +" </if> "
		    + " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
		    + " LIMIT #{start},#{length} "
		    + " </script>")
	public  List<HashMap<String, Object>> getDatatable2(DatatableInput datatableInput, @Param("fullsearch") String fullsearch);

	@Select("<script>"
			+ "select count(*) from ${service} "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE description like '%${search}%' or name like '%${search}%' "
		    +" </if> "
		    + " </script>")
	public Integer getPartialSize(DatatableInput datatableInput);

}

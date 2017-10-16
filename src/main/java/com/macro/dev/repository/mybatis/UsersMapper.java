package com.macro.dev.repository.mybatis;

import com.macro.dev.models.TcPgm;
import com.macro.dev.models.TcRole;
import com.macro.dev.models.TcUser;
import com.macro.dev.models.TcUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Select("select * from tc_user")
/*    @Results({
            @Result(id=true, property="userNm", column="user_nm"),
            @Result(property="userPw", column="user_pw")
    })*/
    List<TcUser> findAll();

    @Select("select * from users")
    TcUser findByUsername();

    @Select("select * from tc_user t where t.user_id=#{user_id}")
    TcUser findById(Long user_id);

    @Insert("insert into users(name,salary) values(#{name},#{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
            before = false, resultType = Integer.class)
    void insert(TcUser users);



    TcUser selectUserById(Long id);

    @Select("select * from tc_user where user_nm=#{username}")
    @Results(value = {
            @Result(property="tcUserRoles", javaType=List.class, column="user_id",  many=@Many(select="getLnkRoles"))
    })
    TcUser selectUserByUsername(String username);

    @Select("SELECT * FROM tc_user_role WHERE user_id = #{user_id}")
    @Results(value = {
         //   @Result(property="tcUser", javaType=String.class, column="role_id",  one=@One(select="getRoles")),
            @Result(property="tcRole", javaType=String.class, column="role_id",  one=@One(select="getRoles"))
    })
    List<TcUserRole> getLnkRoles(String user_id);

    @Select("select * from tc_role where role_id=#{role_id}")
    TcRole getRoles(String role_id);

    List<TcUser> selectAllUsers();

    @Insert("insert into tc_user (user_id,org_cd,user_nm,user_pw) values(tc_user_seq.nextval,#{org_cd},#{user_nm},#{user_pw})")
    Integer insertUser(TcUser user);

    Integer updateUserOnPasswordById(TcUser user);

    Integer deleteUserById(Long id);

    @Update("UPDATE tc_user SET org_cd=#{org_cd},user_nm=#{user_nm},user_pw=#{user_pw} WHERE user_id =#{user_id}")
    void updateUser(TcUser norg);
}

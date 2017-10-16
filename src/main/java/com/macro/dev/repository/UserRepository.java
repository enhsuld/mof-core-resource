package com.macro.dev.repository;

import com.macro.dev.models.TcUser;

import java.util.List;


public interface UserRepository {

    TcUser selectUserById(Long id);

    TcUser selectUserByUsername(String username);

    List<TcUser> selectAllUsers();

    Integer insertUser(TcUser user);

    Integer updateUserOnPasswordById(TcUser user);

    Integer deleteUserById(Long id);

}

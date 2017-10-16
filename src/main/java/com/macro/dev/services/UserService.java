package com.macro.dev.services;

import com.macro.dev.models.TcUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;



public interface UserService extends UserDetailsService {

    Optional<TcUser> getUserById(Long id);

    boolean saveUser(TcUser user);

    boolean modifyUserOnPasswordById(TcUser user);

    boolean deleteUserById(Long id);

    TcUser selectUserByUsername(String username);

    List<TcUser> selectAllUsers();
}

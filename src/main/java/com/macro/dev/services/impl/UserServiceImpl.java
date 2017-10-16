package com.macro.dev.services.impl;

import com.macro.dev.repository.mybatis.UsersMapper;
import com.macro.dev.models.TcUser;
import com.macro.dev.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    private final UsersMapper usersMapper;


    public UserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Optional<TcUser> getUserById(Long id) {
        return Optional.ofNullable(usersMapper.selectUserById(id));
    }

    @Override
    public boolean saveUser(TcUser user) {
        return usersMapper.insertUser(user) > 0;
    }

    @Override
    public boolean modifyUserOnPasswordById(TcUser user) {
        return usersMapper.updateUserOnPasswordById(user) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return usersMapper.deleteUserById(id) > 0;
    }

    @Override
    public TcUser selectUserByUsername(String username) {
        return usersMapper.selectUserByUsername(username);
    }

    @Override
    public List<TcUser> selectAllUsers() {
        return usersMapper.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

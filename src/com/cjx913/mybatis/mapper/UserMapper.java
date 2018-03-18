package com.cjx913.mybatis.mapper;

import com.cjx913.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAllUser() throws Exception;
    public User findUserById(int id) throws Exception;
    public User findUserByName(String name) throws Exception;
    public List<User> findUserByLikeName(String name) throws Exception;
    public void insertUser(User user) throws Exception;
    public void updateUser(User user) throws Exception;
    public void deleteUser(User user) throws Exception;
}

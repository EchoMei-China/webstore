package com.example.service.impl;

import java.util.List;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUserPage(Integer startRows) {
        return userMapper.queryUserPage(startRows);
    }

    @Override
    public List<User> selectUserPage(String userName, String userSex, Integer startRows) {
        return userMapper.selectUserPage(userName, userSex, startRows);
    }

    @Override
    public Integer getRowCount(String userName, String userSex) {
        return userMapper.getRowCount(userName, userSex);
    }

    @Override
    public Integer createUser(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public Integer deleteUserById(String userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public Integer deleteUserByIdList(@Param("list") List userIds) {
        return userMapper.deleteUserByIdList(userIds);
    }

    @Override
    public Integer updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public Integer checkUserNAme(@Param("uName") String uName) {
        return userMapper.checkUserNAme(uName);
    }

    @Override
    public Integer checkUserPhone(@Param("uPhone") String uPhone) {
        return userMapper.checkUserPhone(uPhone);
    }

    @Override
    public Integer Registered(@Param("user_nickname") String user_nickname, @Param("user_phone") String user_phone, @Param("user_password") String user_password) {
        return userMapper.Registered(user_nickname, user_phone, user_password);
    }

    @Override
    public User determineUserByNickName(@Param("userIpt") String userIpt, @Param("userPsd") String userPsd) {
        return userMapper.determineUserByNickName(userIpt, userPsd);
    }

    @Override
    public User queryUserByUserNickname(@Param("user_nickname") String user_nickname) {
        return userMapper.queryUserByUserNickname(user_nickname);
    }

    @Override
    public User queryUserById(@Param("user_id") Integer user_id) {
        return userMapper.queryUserById(user_id);
    }

    @Override
    public Integer updateChange(@Param("user_id") Integer user_id) {
        return userMapper.updateChange(user_id);
    }

    @Override
    public int addChange(@Param("user_id") Integer user_id) {
        return userMapper.addChange(user_id);
    }
}
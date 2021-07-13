package com.example.controller;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserPage")
    @ResponseBody
    public List<User> queryUserPage(Integer page) {
        int pageNow = page == null ? 1 : page;
        int pageSize = 5;
        int startRows = pageSize*(pageNow-1);
        return userService.queryUserPage(startRows);
    }

    @RequestMapping("/selectUserPage")
    @ResponseBody
    public List<User> selectUserPage(String userName, String userSex, Integer page) {
        int pageNow = page == null ? 1 : page;
        int pageSize = 5;
        int startRows = pageSize*(pageNow-1);
        return userService.selectUserPage(userName, userSex, startRows);
    }

    @RequestMapping("/getRowCount")
    @ResponseBody
    public Integer getRowCount(String userName, String userSex) {
        return userService.getRowCount(userName, userSex);
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Integer createUser(User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Integer deleteUserById(String userId) {
        return userService.deleteUserById(userId);
    }

    @RequestMapping(value = "/deleteUserByIdList")
    @ResponseBody
    public Integer deleteUserByIdList(String userIdList) {
        String userIdListSub = userIdList.substring(0, userIdList.length()-1);
//        String[] userIds = userIdList.split(",");

        List userIds = new ArrayList();
        for (String userIdStr: userIdListSub.split(",")){
            userIds.add(userIdStr.trim());
        }
        return userService.deleteUserByIdList(userIds);
    }

    @RequestMapping("/updateUserById")
    @ResponseBody
    public Integer updateUserById(User user) {
        return userService.updateUserById(user);
    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public Integer checkUserName(String uName) {
        int number = userService.checkUserNAme(uName);
        return number;
    }

    @RequestMapping("/checkUserPhone")
    @ResponseBody
    public Integer checkUserPhone(String uPhone) {
        int number = userService.checkUserPhone(uPhone);
        return number;
    }

    @RequestMapping("/registered")
    @ResponseBody
    public Integer registered(String user_nickname, String user_phone, String user_password) {
        return userService.Registered(user_nickname, user_phone, user_password);
    }

    @RequestMapping(value = "/obtainLoginMessage")
    @ResponseBody
    public Map<String, Object> obtainLoginMessage(String userIpt, String userPsd) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.determineUserByNickName(userIpt, userPsd);
        if (userPsd.equals(user.getUserPassword())) {
            map.put("msg","success");
            map.put("user", user);
        } else {
            map.put("msg","用户名或密码错误");
        }
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainUserIdByNickname")
    @ResponseBody
    public Map<String, Object> botainUserIdByNickname(String user_nickname){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.queryUserByUserNickname(user_nickname);
        map.put("user",user);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainUserById")
    @ResponseBody
    public Map<String, Object> obtainUserById(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.queryUserById(user_id);
        map.put("user",user);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/updateChange")
    @ResponseBody
    public Integer updateChange(Integer user_id) {
        return userService.updateChange(user_id);
    }

    @RequestMapping("/addChange")
    @ResponseBody
    public Integer addChange(Integer user_id) {
        return userService.addChange(user_id);
    }
}
package com.example.controller;

import com.example.pojo.Admin;
import org.springframework.ui.Model;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(String aname,String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        Admin admin = adminService.queryAdminByUsername(aname);
        if (password.equals(admin.getPassword())) {
            map.put("msg","success");
        } else {
            map.put("msg","用户名或密码错误");
        }
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAllAdmins")
    @ResponseBody
    public Map<String, Object> obtainAllAdmins(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Admin> admins = adminService.queryAllAdmins();
        map.put("admins",admins);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAllAdminsCount")
    @ResponseBody
    public Integer obtainAllAdminsCount() {
        return adminService.queryAllAdminsCount();
    }

    @RequestMapping("/ManageCurrentAdmins")
    @ResponseBody
    public Map<String, Object> ManageCurrentAdmins(Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Admin> admins = adminService.queryCurrentAdmin(startIndex, pageSize);
        map.put("admins",admins);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAdminByAid")
    @ResponseBody
    public Map<String, Object> obtainAdminByAid(Integer aid){
        Map<String, Object> map = new HashMap<String, Object>();
        Admin admin = adminService.queryByAid(aid);
        map.put("admin",admin);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/changeAdminByAid")
    @ResponseBody
    public Integer changeAdminByAid(Integer aid, String aname, String password) {
        return adminService.updateByAid(aid, aname, password);
    }

    @RequestMapping("/insertNewAdmin")
    @ResponseBody
    public Integer insertNewAdmin(String aname, String password) {
        return adminService.insertAdmin(aname, password);
    }

    @RequestMapping("/obtainAdminBysearch")
    @ResponseBody
    public Map<String, Object> obtainAdminBysearch(String searchInformation){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Admin> admins = adminService.queryAdminBySearch(searchInformation);
        map.put("admins",admins);
        System.out.println(map);
        return map;
    }
}

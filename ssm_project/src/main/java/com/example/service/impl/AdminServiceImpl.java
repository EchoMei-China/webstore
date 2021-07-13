package com.example.service.impl;

import com.example.mapper.AdminMapper;
import com.example.pojo.Admin;
import com.example.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public Admin queryAdminByUsername(String parameter) {

        return mapper.queryAdminByUsername(parameter);
    }

    @Override
    public List<Admin> queryAllAdmins() {
        return mapper.queryAllAdmins();
    }

    @Override
    public int queryAllAdminsCount() {
        return mapper.queryAllAdminsCount();
    }

    @Override
    public List<Admin> queryCurrentAdmin(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentAdmin(startIndex, pageSize);
    }

    @Override
    public Admin queryByAid(@Param("aid") Integer aid) {
        return mapper.queryByAid(aid);
    }

    @Override
    public int updateByAid(@Param("aid") Integer aid, @Param("aname") String aname, @Param("password") String password) {
        return mapper.updateByAid(aid, aname, password);
    }

    @Override
    public int insertAdmin(@Param("aname") String aname, @Param("password") String password) {
        return mapper.insertAdmin(aname, password);
    }

    @Override
    public List<Admin> queryAdminBySearch(@Param("searchInformation") String searchInformation) {
        return mapper.queryAdminBySearch(searchInformation);
    }
}

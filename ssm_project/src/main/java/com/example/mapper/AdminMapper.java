package com.example.mapper;

import com.example.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    /**
     * 根据管理员姓名返回相应的用户
     * @param parameter
     * @return Admin
     */
     Admin queryAdminByUsername(@Param("aname") String parameter);

    /**
     * 获取全部管理员记录
     */
    List<Admin> queryAllAdmins();

    /**
     * 获取总记录数
     */
    int queryAllAdminsCount();

    /**
     * 总记录分页
     */
    List<Admin> queryCurrentAdmin(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据aid获取admin
     */
    Admin queryByAid(@Param("aid") Integer aid);

    /**
     * 根据aid修改admin
     */
    int updateByAid(@Param("aid") Integer aid, @Param("aname") String aname, @Param("password") String password);

    /**
     * 新增admin
     */
    int insertAdmin(@Param("aname") String aname, @Param("password") String password);

    /**
     * 根据搜索获取
     */
    List<Admin> queryAdminBySearch(@Param("searchInformation") String searchInformation);

}

package com.example.service;

import java.util.List;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    /**
     * 分页查询 User
     * @param startRows 起始页
     * @return List<User>
     */
    List<User> queryUserPage(Integer startRows);

    /**
     * 分页查询 User 带条件
     * @param userName
     * @param userSex
     * @param startRows
     * @return
     */
    List<User> selectUserPage(@Param("userName")String userName, @Param("userSex")String userSex, @Param("startRows")Integer startRows);

    /**
     * 查询 User 个数
     * @param userName
     * @param userSex
     * @return
     */
    Integer getRowCount(@Param("userName")String userName, @Param("userSex")String userSex);

    /**
     * 添加 User
     * @param user
     * @return 返回码
     */
    Integer createUser(User user);

    /**
     * 根据 userId 删除用户
     * @return 返回码
     */
    Integer deleteUserById(String userId);

    /**
     * 根据 userId 批量删除用户
     * @param userIds
     * @return
     */
    Integer deleteUserByIdList(@Param("list") List userIds);

    /**
     * 根据 userId 更新用户
     * @return 返回码
     */
    Integer updateUserById(User user);

    /**
     *  根据用户名查询是否已经存在该用户名
     */
    Integer checkUserNAme(@Param("uName") String uName);

    /**
     *  根据手机号查询是否已经存在该手机号
     */
    Integer checkUserPhone(@Param("uPhone") String uPhone);

    /**
     * 注册
     * 返回状态码
     */
    Integer Registered(@Param("user_nickname") String user_nickname, @Param("user_phone") String user_phone, @Param("user_password") String user_password);

    /**
     * 登录
     */
    User determineUserByNickName(@Param("userIpt") String userIpt, @Param("userPsd") String userPsd);

    /**
     * 根据用户昵称寻找用户id
     */
    User queryUserByUserNickname(@Param("user_nickname") String user_nickname);

    /**
     * 根据user_id查询
     */
    User queryUserById(@Param("user_id") Integer user_id);

    /**
     * 根据user_id修改change
     */
    Integer updateChange(@Param("user_id") Integer user_id);

    /**
     * 根据user_id,增加change
     */
    int addChange(@Param("user_id") Integer user_id);
}
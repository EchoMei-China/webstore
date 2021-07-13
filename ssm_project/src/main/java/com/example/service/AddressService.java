package com.example.service;

import com.example.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {

    /**
     * 添加地址
     */
    Integer createNewAddress(@Param("user_id") Integer user_id, @Param("uname") String uname, @Param("uphone") String phone, @Param("pname") String pname, @Param("cname") String cname, @Param("dname") String dname, @Param("area") String area, @Param("postalcode") String postalcode);

    /**
     * 根据用户id展示地址
     */
    List<Address> queryAddressByUserId(@Param("user_id") Integer user_id);

    /**
     * 根据用户id计算地址总数
     */
    Integer queryCountByUserId(@Param("user_id") Integer user_id);

    /**
     * 根据adid删除地址
     */
    Integer deleteAddress(@Param("adid") Integer adid);

    /**
     * 根据adid查询地址
     */
    Address queryAddressByAdid(@Param("adid") Integer adid);

    /**
     * 根据adid修改地址信息
     */
    Integer updateAddress(@Param("adid") Integer adid, @Param("uname") String uname, @Param("uphone") String uphone, @Param("pname") String pname, @Param("cname") String cname, @Param("dname") String dname, @Param("area") String area, @Param("postalcode") String postalcode);
}

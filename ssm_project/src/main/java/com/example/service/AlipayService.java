package com.example.service;

import com.example.pojo.Alipay;
import org.apache.ibatis.annotations.Param;

public interface AlipayService {

    /**
     * 根据user_id查询
     */
    Alipay queryAlipayByUserid(@Param("user_id") Integer user_id);

    /**
     * 根据apid查询
     */
    Alipay queryByApid(@Param("apid") Integer apid);

    /**
     * 根据apid删除
     */
    int deleteAlipay(@Param("apid") Integer apid);

    /**
     * 添加新记录
     */
    int insertAlipay(@Param("user_id") Integer user_id, @Param("apnum") String apnum, @Param("appsd") String appsd, @Param("appaypsd") String appaypsd);

}

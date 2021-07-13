package com.example.service.impl;


import com.example.mapper.AlipayMapper;
import com.example.pojo.Alipay;
import com.example.service.AlipayService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayMapper mapper;

    @Override
    public Alipay queryAlipayByUserid(@Param("user_id") Integer user_id) {
        return mapper.queryAlipayByUserid(user_id);
    }

    @Override
    public Alipay queryByApid(@Param("apid") Integer apid) {
        return mapper.queryByApid(apid);
    }

    @Override
    public int deleteAlipay(@Param("apid") Integer apid) {
        return mapper.deleteAlipay(apid);
    }

    @Override
    public int insertAlipay(@Param("user_id") Integer user_id, @Param("apnum") String apnum, @Param("appsd") String appsd, @Param("appaypsd") String appaypsd) {
        return mapper.insertAlipay(user_id, apnum, appsd, appaypsd);
    }

}

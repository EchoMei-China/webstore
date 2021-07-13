package com.example.service.impl;

import com.example.mapper.WechatMapper;
import com.example.pojo.Wechat;
import com.example.service.WechatService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WechatMapper mapper;

    @Override
    public Wechat queryWechatByUserid(@Param("user_id") Integer user_id) {
        return mapper.queryWechatByUserid(user_id);
    }

    @Override
    public Wechat queryByWcid(@Param("wcid") Integer wcid) {
        return mapper.queryByWcid(wcid);
    }

    @Override
    public int deleteWechat(@Param("wcid") Integer wcid) {
        return mapper.deleteWechat(wcid);
    }

    @Override
    public int insertWechat(@Param("user_id") Integer user_id, @Param("wcnum") String wcnum, @Param("wcpsd") String wcpsd, @Param("wcpaypsd") String wcpaypsd) {
        return mapper.insertWechat(user_id, wcnum, wcpsd, wcpaypsd);
    }
}

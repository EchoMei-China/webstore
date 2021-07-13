package com.example.mapper;

import com.example.pojo.Wechat;
import org.apache.ibatis.annotations.Param;

public interface WechatMapper {

    /**
     * 根据user_id查询WeChat
     */
    Wechat queryWechatByUserid(@Param("user_id") Integer user_id);

    /**
     * 根据wcid查询
     */
    Wechat queryByWcid(@Param("wcid") Integer wcid);

    /**
     * 根据wcid删除
     */
    int deleteWechat(@Param("wcid") Integer wcid);

    /**
     * 添加新记录
     */
    int insertWechat(@Param("user_id") Integer user_id, @Param("wcnum") String wcnum, @Param("wcpsd") String wcpsd, @Param("wcpaypsd") String wcpaypsd);
}

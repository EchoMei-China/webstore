package com.example.mapper;

import com.example.pojo.Wallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WalletMapper {

    /**
     * 根据user_id 查询对应的bk记录
     */
    List<Wallet> queryWalletByUserId(@Param("user_id") Integer user_id);

    /**
     * 添加wallet记录
     */
    Integer insertWallet(@Param("user_id") Integer user_id, @Param("bkid") Integer bkid, @Param("cardtype") String cardtype, @Param("cardnumber") String cardnumber, @Param("cardpsd") String cardpsd);

    /**
     * 根据bkid和cardnumber删除记录
     */
    Integer deleteByBkidAndCardnumber(@Param("bkid") Integer bkid, @Param("cardnumber") String cardnumber);

    /**
     * 根据bkid和cardnumber查找记录
     */
    Integer queryByBkidAndCardno(@Param("bkid") Integer bkid, @Param("cardnumber") String cardnumber);

    /**
     * 根据wid查找记录
     */
    Wallet queryByWid(@Param("wid") Integer wid);
}

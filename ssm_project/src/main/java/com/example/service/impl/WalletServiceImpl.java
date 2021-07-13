package com.example.service.impl;

import com.example.mapper.WalletMapper;
import com.example.pojo.Wallet;
import com.example.service.WalletService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletMapper mapper;

    @Override
    public List<Wallet> queryWalletByUserId(@Param("user_id") Integer user_id) {
        return mapper.queryWalletByUserId(user_id);
    }

    @Override
    public Integer insertWallet(@Param("user_id") Integer user_id, @Param("bkid") Integer bkid, @Param("cardtype") String cardtype, @Param("cardnumber") String cardnumber, @Param("cardpsd") String cardpsd) {
        return mapper.insertWallet(user_id, bkid, cardtype, cardnumber, cardpsd);
    }



    @Override
    public Integer deleteByBkidAndCardnumber(@Param("bkid") Integer bkid, @Param("cardnumber") String cardnumber) {
        return mapper.deleteByBkidAndCardnumber(bkid, cardnumber);
    }

    @Override
    public Integer queryByBkidAndCardno(@Param("bkid") Integer bkid, @Param("cardnumber") String cardnumber) {
        return mapper.queryByBkidAndCardno(bkid, cardnumber);
    }

    @Override
    public Wallet queryByWid(@Param("wid") Integer wid) {
        return mapper.queryByWid(wid);
    }
}

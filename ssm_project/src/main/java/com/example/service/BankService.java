package com.example.service;

import com.example.pojo.Bank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankService {

    /**
     * 查询所有的银行信息
     */
    List<Bank> queryAllBank();

    /**
     * 根据bkname查询bank
     */
    Bank queryBankByBkname(@Param("bkname") String bkname);
}

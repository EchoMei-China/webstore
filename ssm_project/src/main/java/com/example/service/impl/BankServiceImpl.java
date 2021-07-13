package com.example.service.impl;

import com.example.mapper.BankMapper;
import com.example.pojo.Bank;
import com.example.service.BankService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankMapper mapper;

    @Override
    public List<Bank> queryAllBank() {
        return mapper.queryAllBank();
    }

    @Override
    public Bank queryBankByBkname(@Param("bkname") String bkname) {
        return mapper.queryBankByBkname(bkname);
    }
}

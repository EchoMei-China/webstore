package com.example.controller;

import com.example.pojo.Bank;
import com.example.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping("/obtainBanks")
    @ResponseBody
    public Map<String, Object> obtainBanks(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Bank> banks = bankService.queryAllBank();
        map.put("banknames",banks);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBankByBkame")
    @ResponseBody
    public Map<String, Object> obtainBankByBkame(String bkname){
        Map<String, Object> map = new HashMap<String, Object>();
        Bank bank = bankService.queryBankByBkname(bkname);
        map.put("bank",bank);
        System.out.println(map);
        return map;
    }
}

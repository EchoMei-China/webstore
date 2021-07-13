package com.example.controller;

import com.example.pojo.Wallet;
import com.example.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping("/botainBankcardByUerId")
    @ResponseBody
    public Map<String, Object> botainBankcardByUerId(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Wallet> wallets = walletService.queryWalletByUserId(user_id);
        map.put("banks",wallets);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/insertWallet")
    @ResponseBody
    public Integer insertWallet(Integer user_id, Integer bkid, String cardtype, String cardnumber, String cardpsd) {
        return walletService.insertWallet(user_id, bkid, cardtype, cardnumber, cardpsd);
    }

    @RequestMapping("/deleteWallet")
    @ResponseBody
    public Integer deleteWallet(Integer bkid, String cardnumber) {
        return walletService.deleteByBkidAndCardnumber(bkid, cardnumber);
    }

    @RequestMapping("/obtaniWalletByBkidAndCardno")
    @ResponseBody
    public Integer obtaniWalletByBkidAndCardno(Integer bkid, String cardnumber) {
        return walletService.queryByBkidAndCardno(bkid, cardnumber);
    }

    @RequestMapping("/confirmBankCard")
    @ResponseBody
    public Map<String, Object> confirmBankCard(Integer wid){
        Map<String, Object> map = new HashMap<String, Object>();
        Wallet wallet = walletService.queryByWid(wid);
        map.put("wallet",wallet);
        System.out.println(map);
        return map;
    }
}

package com.example.controller;


import com.example.pojo.Alipay;
import com.example.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/obtainAlipay")
    @ResponseBody
    public Map<String, Object> obtainAlipay(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        Alipay alipay = alipayService.queryAlipayByUserid(user_id);
        map.put("alipay",alipay);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/confirmAlipay")
    @ResponseBody
    public Map<String, Object> confirmAlipay(Integer apid){
        Map<String, Object> map = new HashMap<String, Object>();
        Alipay alipay = alipayService.queryByApid(apid);
        map.put("alipay",alipay);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/deleteAlipay")
    @ResponseBody
    public Integer deleteAlipay(Integer apid) {
        return alipayService.deleteAlipay(apid);
    }

    @RequestMapping("/insertAlipay")
    @ResponseBody
    public Integer insertAlipay(Integer user_id, String apnum, String appsd, String appaypsd) {
        return alipayService.insertAlipay(user_id, apnum, appsd, appaypsd);
    }
}

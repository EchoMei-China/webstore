package com.example.controller;

import com.example.pojo.Wechat;
import com.example.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @RequestMapping("/obtainWechat")
    @ResponseBody
    public Map<String, Object> obtainWechat(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        Wechat wechat = wechatService.queryWechatByUserid(user_id);
        map.put("wechat",wechat);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/confirmWechat")
    @ResponseBody
    public Map<String, Object> confirmWechat(Integer wcid){
        Map<String, Object> map = new HashMap<String, Object>();
        Wechat wechat = wechatService.queryByWcid(wcid);
        map.put("wechat",wechat);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/deleteWechat")
    @ResponseBody
    public Integer deleteWechat(Integer wcid) {
        return wechatService.deleteWechat(wcid);
    }

    @RequestMapping("/insertWechat")
    @ResponseBody
    public Integer insertWechat(Integer user_id, String wcnum, String wcpsd, String wcpaypsd) {
        return wechatService.insertWechat(user_id, wcnum, wcpsd, wcpaypsd);
    }
}

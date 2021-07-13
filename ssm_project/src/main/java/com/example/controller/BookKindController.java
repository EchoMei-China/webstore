package com.example.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.pojo.BookKind;
import com.example.service.BookKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.Map;

@Controller
@RequestMapping("/bookkind")
public class BookKindController {

    @Autowired
    private BookKindService bookkindService;

    @RequestMapping("/obtainKind")
    @ResponseBody
    public Map<String, Object> obtainKind(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<BookKind> bookkind = bookkindService.queryBookKind();
        map.put("lis",bookkind);
        return map;
    }

    @RequestMapping("/obtainBookKindName")
    @ResponseBody
    public Map<String, Object> obtainBookKindName(Integer tkid){
        Map<String, Object> map = new HashMap<String, Object>();
        BookKind bookkind = bookkindService.queryByKid(tkid);
        map.put("kinds",bookkind);
        return map;
    }

    @RequestMapping("/obtainKidByKname")
    @ResponseBody
    public Map<String, Object> obtainKidByKname(String kname){
        Map<String, Object> map = new HashMap<String, Object>();
        BookKind bookkind = bookkindService.queryByKname(kname);
        map.put("kinds",bookkind);
        return map;
    }
}

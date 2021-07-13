package com.example.controller;

import com.example.pojo.BookType;
import com.example.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/booktype")
public class BookTypeController {

    @Autowired
    private BookTypeService booktypeService;

    @RequestMapping("/obtainType")
    @ResponseBody
    public Map<String, Object> obtainType(Integer tkid){
        Map<String, Object> map = new HashMap<String, Object>();
        List<BookType> booktype = booktypeService.queryByBookKind(tkid);
        map.put("types",booktype);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBookTypeName")
    @ResponseBody
    public Map<String, Object> obtainBookTypeName(Integer btid){
        Map<String, Object> map = new HashMap<String, Object>();
        BookType booktype = booktypeService.queryByTid(btid);
        map.put("types",booktype);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainTidByTname")
    @ResponseBody
    public Map<String, Object> obtainTidByTname(String tname){
        Map<String, Object> map = new HashMap<String, Object>();
        BookType booktype = booktypeService.queryByTname(tname);
        map.put("booktype",booktype);
        System.out.println(map);
        return map;
    }
}

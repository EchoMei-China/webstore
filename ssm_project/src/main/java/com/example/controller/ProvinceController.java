package com.example.controller;

import com.example.pojo.Province;
import com.example.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("/obtainProvince")
    @ResponseBody
    public Map<String, Object> obtainProvince(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Province> provinces = provinceService.queryAllProvinces();
        map.put("provinces",provinces);
        System.out.println(map);
        return map;
    }
}

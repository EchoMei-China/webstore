package com.example.controller;

import com.example.pojo.City;
import com.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/obtainCity")
    @ResponseBody
    public Map<String, Object> obtainCity(String pname){
        Map<String, Object> map = new HashMap<String, Object>();
        List<City> citys = cityService.queryCitysByPid(pname);
        map.put("citys",citys);
        System.out.println(map);
        return map;
    }
}

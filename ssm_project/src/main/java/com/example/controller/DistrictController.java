package com.example.controller;

import com.example.mapper.DistrictMapper;
import com.example.pojo.City;
import com.example.pojo.District;
import com.example.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("/obtainDistrict")
    @ResponseBody
    public Map<String, Object> obtainCity(String cname){
        Map<String, Object> map = new HashMap<String, Object>();
        List<District> districts = districtService.queryDistrictByCname(cname);
        map.put("districts",districts);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainShippingByDname")
    @ResponseBody
    public Map<String, Object> obtainShippingByDname(String dname){
        Map<String, Object> map = new HashMap<String, Object>();
        List<District> district = districtService.queryShippingByDname(dname);
        map.put("district",district);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAllDistricts")
    @ResponseBody
    public Map<String, Object> obtainAllDistricts(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<District> districts = districtService.queryAllDistricts();
        map.put("districts",districts);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/ManageCurrentContentShipping")
    @ResponseBody
    public Map<String, Object> ManageCurrentContentShipping(Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<District> districts = districtService.queryCurrentDistricts(startIndex, pageSize);
        map.put("districts",districts);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/changeShipping")
    @ResponseBody
    public Integer changeShipping(Integer pid, Integer shipping) {
        return districtService.changeShipping(pid, shipping);
    }

    @RequestMapping("/searchDistricts")
    @ResponseBody
    public Map<String, Object> searchDistricts(String searchInformation){
        Map<String, Object> map = new HashMap<String, Object>();
        List<District> districts = districtService.queryBySearch(searchInformation);
        map.put("districts",districts);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAllCount")
    @ResponseBody
    public Integer obtainAllCount() {
        return districtService.queryAllcount();
    }
}

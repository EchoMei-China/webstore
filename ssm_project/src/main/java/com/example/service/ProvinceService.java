package com.example.service;

import com.example.pojo.Province;

import java.util.List;

public interface ProvinceService {

    /**
     * 获取所有的省份
     */
    List<Province> queryAllProvinces();
}

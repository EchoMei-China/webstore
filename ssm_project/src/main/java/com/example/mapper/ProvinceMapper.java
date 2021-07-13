package com.example.mapper;

import com.example.pojo.Province;

import java.util.List;

public interface ProvinceMapper {

    /**
     * 获取所有的省份
     */
    List<Province> queryAllProvinces();
}

package com.example.service.impl;

import com.example.mapper.ProvinceMapper;
import com.example.pojo.Province;
import com.example.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper mapper;

    @Override
    public List<Province> queryAllProvinces() {
        return mapper.queryAllProvinces();
    }
}

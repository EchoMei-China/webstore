package com.example.service.impl;

import com.example.mapper.CityMapper;
import com.example.pojo.City;
import com.example.service.CityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper mapper;

    @Override
    public List<City> queryCitysByPid(@Param("pname") String pname) {
        return mapper.queryCitysByPid(pname);
    }
}

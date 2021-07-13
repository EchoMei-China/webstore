package com.example.service;

import com.example.pojo.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityService {

    /**
     * 根据传递过来的省份id查询此省份的城市
     */
    List<City> queryCitysByPid(@Param("pname") String pname);
}

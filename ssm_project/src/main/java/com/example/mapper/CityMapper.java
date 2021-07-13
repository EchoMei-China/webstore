package com.example.mapper;

import com.example.pojo.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {

    /**
     * 根据传递过来的省份名pname查询此省份的城市
     */
    List<City>  queryCitysByPid(@Param("pname") String pname);
}

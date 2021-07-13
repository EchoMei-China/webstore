package com.example.service.impl;

import com.example.mapper.DistrictMapper;
import com.example.pojo.District;
import com.example.service.DistrictService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper mapper;

    @Override
    public List<District> queryDistrictByCname(@Param("cname") String cname) {
        return mapper.queryDistrictByCname(cname);
    }

    @Override
    public List<District> queryShippingByDname(@Param("dname") String dname) {
        return mapper.queryShippingByDname(dname);
    }

    @Override
    public List<District> queryAllDistricts() {
        return mapper.queryAllDistricts();
    }

    @Override
    public List<District> queryCurrentDistricts(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentDistricts(startIndex, pageSize);
    }

    @Override
    public  int changeShipping(@Param("pid") Integer pid, @Param("shipping") Integer shipping) {
        return mapper.changeShipping(pid, shipping);
    }

    @Override
    public List<District> queryBySearch(@Param("searchInformation") String searchInformation) {
        return mapper.queryBySearch(searchInformation);
    }

    @Override
    public int queryAllcount() {
        return mapper.queryAllcount();
    }
}

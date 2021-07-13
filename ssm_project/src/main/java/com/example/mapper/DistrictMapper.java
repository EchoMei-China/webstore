package com.example.mapper;

import com.example.pojo.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {

    /**
     * 根据cname获取对应的所有地区
     */
    List<District> queryDistrictByCname(@Param("cname") String cname);

    /**
     * 根据所选择的地区名来获得对应的运费
     */
    List<District> queryShippingByDname(@Param("dname") String dname);

    /**
     * 获取所有的记录
     */
    List<District> queryAllDistricts();

    /**
     * 全部记录分页
     */
    List<District> queryCurrentDistricts(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据pid修改shipping
     */
    int changeShipping(@Param("pid") Integer pid, @Param("shipping") Integer shipping);

    /**
     * 根据pname模糊查询district
     */
    List<District> queryBySearch(@Param("searchInformation") String searchInformation);

    /**
     * 获取总数
     */
    int queryAllcount();
}

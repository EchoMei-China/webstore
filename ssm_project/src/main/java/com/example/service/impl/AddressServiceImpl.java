package com.example.service.impl;

import com.example.mapper.AddressMapper;
import com.example.pojo.Address;
import com.example.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper mapper;

    @Override
    public Integer createNewAddress(@Param("user_id") Integer user_id, @Param("uname") String uname, @Param("uphone") String phone, @Param("pname") String pname, @Param("cname") String cname, @Param("dname") String dname, @Param("area") String area, @Param("postalcode") String postalcode) {
        return mapper.createNewAddress(user_id, uname, phone, pname, cname, dname, area, postalcode);
    }

    @Override
    public List<Address> queryAddressByUserId(@Param("user_id") Integer user_id) {
        return mapper.queryAddressByUserId(user_id);
    }

    @Override
    public Integer queryCountByUserId(@Param("user_id") Integer user_id) {
        return mapper.queryCountByUserId(user_id);
    }

    @Override
    public Integer deleteAddress(@Param("adid") Integer adid) {
        return mapper.deleteAddress(adid);
    }

    @Override
    public Address queryAddressByAdid(@Param("adid") Integer adid) {
        return mapper.queryAddressByAdid(adid);
    }

    @Override
    public Integer updateAddress(@Param("adid") Integer adid, @Param("uname") String uname, @Param("uphone") String uphone, @Param("pname") String pname, @Param("cname") String cname, @Param("dname") String dname, @Param("area") String area, @Param("postalcode") String postalcode) {
        return mapper.updateAddress(adid, uname, uphone, pname, cname, dname, area, postalcode);
    }
}

package com.example.controller;

import com.example.pojo.Address;
import com.example.service.AddressService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/insertNewAddress")
    @ResponseBody
    public Integer insertNewAddress(Integer user_id, String uname, String uphone, String pname, String cname, String dname, String area, String postalcode) {
        return addressService.createNewAddress(user_id, uname, uphone, pname, cname, dname, area, postalcode);
    }

    @RequestMapping("/obtainAddressByUserId")
    @ResponseBody
    public Map<String, Object> obtainAddressByUserId(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Address> addresses = addressService.queryAddressByUserId(user_id);
        map.put("addresses",addresses);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainAddressCount")
    @ResponseBody
    public Integer obtainAddressCount(Integer user_id) {
        return addressService.queryCountByUserId(user_id);
    }

    @RequestMapping("/deleteAddress")
    @ResponseBody
    public Integer deleteAddress(Integer adid) {
        return addressService.deleteAddress(adid);
    }

    @RequestMapping("/obtainAddressByAdid")
    @ResponseBody
    public Map<String, Object> obtainAddressByAdid(Integer adid){
        Map<String, Object> map = new HashMap<String, Object>();
        Address address = addressService.queryAddressByAdid(adid);
        map.put("address",address);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/updateAddress")
    @ResponseBody
    public Integer updateAddress(Integer adid, String uname, String uphone, String pname, String cname, String dname, String area, String postalcode) {
        return addressService.updateAddress(adid, uname, uphone, pname, cname, dname, area, postalcode);
    }

}

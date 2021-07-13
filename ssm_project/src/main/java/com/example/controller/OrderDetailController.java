package com.example.controller;

import com.example.pojo.OrderDetail;
import com.example.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/insertOrderdetail")
    @ResponseBody
    public Integer insertOrderdetail(String oid, Integer bid, Integer odquantity, String odprice) {
        return orderDetailService.insertOrderDetail(oid, bid, odquantity, odprice);
    }

    @RequestMapping("/obotainAllOrderdetails")
    @ResponseBody
    public Map<String, Object> obotainAllOrderdetails(String oid){
        Map<String, Object> map = new HashMap<String, Object>();
        List<OrderDetail> orderdetails = orderDetailService.queryAllOrderdetails(oid);
        map.put("orderdetails",orderdetails);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/deleteOrderdetailByOid")
    @ResponseBody
    public Integer deleteOrderdetailByOid(String oid) {
        return orderDetailService.deleteByOid(oid);
    }
}

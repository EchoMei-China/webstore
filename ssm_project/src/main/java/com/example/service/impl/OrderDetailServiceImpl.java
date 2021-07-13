package com.example.service.impl;

import com.example.mapper.OrderDetailMapper;
import com.example.pojo.OrderDetail;
import com.example.service.OrderDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper mapper;

    @Override
    public Integer insertOrderDetail(@Param("oid") String oid, @Param("bid") Integer bid, @Param("odquantity") Integer odquantity, @Param("odprice") String odprice) {
        return mapper.insertOrderDetail(oid, bid, odquantity, odprice);
    }

    @Override
    public List<OrderDetail> queryAllOrderdetails(@Param("oid") String oid) {
        return mapper.queryAllOrderdetails(oid);
    }

    @Override
    public Integer deleteByOid(@Param("oid") String oid) {
        return mapper.deleteByOid(oid);
    }
}

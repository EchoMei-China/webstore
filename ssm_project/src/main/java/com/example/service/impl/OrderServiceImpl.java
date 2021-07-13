package com.example.service.impl;

import com.example.mapper.OrderMapper;
import com.example.pojo.Order;
import com.example.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> ManageAllOrders() {
        return mapper.ManageAllOrders();
    }

    @Override
    public int ManageAllOrderCount() {
        return mapper.ManageAllOrderCount();
    }

    @Override
    public List<Order> ManageAllCurrentOrders(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize){
        return mapper.ManageAllCurrentOrders(startIndex, pageSize);
    }

    @Override
    public Integer insertOrder(@Param("oid") String oid, @Param("user_id") Integer user_id, @Param("useraddress") String useraddress, @Param("otime") String otime, @Param("oprice") String oprice, @Param("shippingpay") Integer shippingpay) {
        return mapper.insertOrder(oid, user_id, useraddress, otime, oprice, shippingpay);
    }

    @Override
    public Integer changeOpay(@Param("oid") String oid) {
        return mapper.changeOpay(oid);
    }

    @Override
    public List<Order> queryAllOrders(@Param("user_id") Integer user_id) {
        return mapper.queryAllOrders(user_id);
    }

    @Override
    public List<Order> queryPayOrders(@Param("user_id") Integer user_id) {
        return mapper.queryPayOrders(user_id);
    }

    @Override
    public List<Order> queryShippingOrders(@Param("user_id") Integer user_id) {
        return mapper.queryShippingOrders(user_id);
    }

    @Override
    public List<Order> querySignforOrders(@Param("user_id") Integer user_id) {
        return mapper.querySignforOrders(user_id);
    }

    @Override
    public List<Order> queryCommentOrders(@Param("user_id") Integer user_id) {
        return mapper.queryCommentOrders(user_id);
    }

    @Override
    public Integer deleteByOid(@Param("oid") String oid) {
        return mapper.deleteByOid(oid);
    }

    @Override
    public Integer changeOcomment(@Param("oid") String oid){
        return mapper.changeOcomment(oid);
    }

    @Override
    public Integer changeOshipping(@Param("oid") String oid){
        return mapper.changeOshipping(oid);
    }

    @Override
    public Integer changeOsignfor(@Param("oid") String oid){
        return mapper.changeOsignfor(oid);
    }

    @Override
    public int queryAllOrderCounts(@Param("user_id") Integer user_id) {
        return mapper.queryAllOrderCounts(user_id);
    }

    @Override
    public int queryPayOrderCounts(@Param("user_id") Integer user_id) {
        return mapper.queryPayOrderCounts(user_id);
    }

    @Override
    public int queryShippingOrderCounts(@Param("user_id") Integer user_id) {
        return mapper.queryShippingOrderCounts(user_id);
    }

    @Override
    public int querySignforOrderCounts(@Param("user_id") Integer user_id) {
        return mapper.querySignforOrderCounts(user_id);
    }

    @Override
    public int queryCommentOrderCounts(@Param("user_id") Integer user_id) {
        return mapper.queryCommentOrderCounts(user_id);
    }

    @Override
    public List<Order> queryCurrentAllOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentAllOrder(user_id, startIndex, pageSize);
    }

    @Override
    public List<Order> queryCurrentPayOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentPayOrder(user_id, startIndex, pageSize);
    }

    @Override
    public List<Order> queryCurrentShippingOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentShippingOrder(user_id, startIndex, pageSize);
    }

    @Override
    public List<Order> queryCurrentSignforOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentSignforOrder(user_id, startIndex, pageSize);
    }

    @Override
    public List<Order> queryCurrentCommentOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentCommentOrder(user_id, startIndex, pageSize);
    }

    @Override
    public int changeAddress(@Param("oid") String oid, @Param("useraddress") String useraddress) {
        return mapper.changeAddress(oid, useraddress);
    }

    @Override
    public List<Order> queryLikeByOid(@Param("oidSnippet") String oidSnippet) {
        return mapper.queryLikeByOid(oidSnippet);
    }
}

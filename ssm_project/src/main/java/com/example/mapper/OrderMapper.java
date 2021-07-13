package com.example.mapper;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    /**
     * 查询所有order
     */
    List<Order> ManageAllOrders();

    /**
     * 获取订单总数
     */
    int ManageAllOrderCount();

    /**
     * 分页获取order
     */
    List<Order> ManageAllCurrentOrders(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据oid模糊查询查询订单
     */
    List<Order> queryLikeByOid(@Param("oidSnippet") String oidSnippet);

    /**
     * 插入order
     */
    Integer insertOrder(@Param("oid") String oid, @Param("user_id") Integer user_id, @Param("useraddress") String useraddress, @Param("otime") String otime, @Param("oprice") String oprice, @Param("shippingpay") Integer shippingpay);

    /**
     * 根据oid修改opay
     */
    Integer changeOpay(@Param("oid") String oid);

    /**
     * 根据user_id获取所有的订单
     */
    List<Order> queryAllOrders(@Param("user_id") Integer user_id);

    /**
     * 根据user_id查询所有 opay=0的订单
     */
    List<Order> queryPayOrders(@Param("user_id") Integer user_id);

    /**
     * 根据user_id查询所有 oshipping=0的订单
     */
    List<Order> queryShippingOrders(@Param("user_id") Integer user_id);

    /**
     * 根据user_id查询所有 osignfor=0的订单
     */
    List<Order> querySignforOrders(@Param("user_id") Integer user_id);

    /**
     * 根据user_id查询所有 ocomment=0的订单
     */
    List<Order> queryCommentOrders(@Param("user_id") Integer user_id);

    /**
     * 根据oid删除
     */
    Integer deleteByOid(@Param("oid") String oid);

    /**
     * 修改ocomment
     */
    Integer changeOcomment(@Param("oid") String oid);

    /**
     * 修改oshipping
     */
    Integer changeOshipping(@Param("oid") String oid);

    /**
     * 修改osignfor
     */
    Integer changeOsignfor(@Param("oid") String oid);

    /**
     * 查询allorders数量
     */
    int queryAllOrderCounts(@Param("user_id") Integer user_id);

    /**
     * 查询payorders数量
     */
    int queryPayOrderCounts(@Param("user_id") Integer user_id);

    /**
     * 查询shippingorders数量
     */
    int queryShippingOrderCounts(@Param("user_id") Integer user_id);

    /**
     * 查询signfororders数量
     */
    int querySignforOrderCounts(@Param("user_id") Integer user_id);

    /**
     * 查询commentorders数量
     */
    int queryCommentOrderCounts(@Param("user_id") Integer user_id);

    /**
     * 根据当前页码查询allorders
     */
    List<Order> queryCurrentAllOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据当前页码查询payorders
     */
    List<Order> queryCurrentPayOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据当前页码查询shippingorders
     */
    List<Order> queryCurrentShippingOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据当前页码查询signfororders
     */
    List<Order> queryCurrentSignforOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据当前页码查询commentorders
     */
    List<Order> queryCurrentCommentOrder(@Param("user_id") Integer user_id, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 修改地址
     */
    int changeAddress(@Param("oid") String oid, @Param("useraddress") String useraddress);
}

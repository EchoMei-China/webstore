package com.example.controller;

import com.example.pojo.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/obtainManageAllOrders")
    @ResponseBody
    public Map<String, Object> obtainManageAllOrders(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.ManageAllOrders();
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainManageAllOrdersCount")
    @ResponseBody
    public Integer obtainManageAllOrdersCount() {
        return orderService.ManageAllOrderCount();
    }

    @RequestMapping("/ManageCurrentContentOrders")
    @ResponseBody
    public Map<String, Object> ManageCurrentContentOrders(Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.ManageAllCurrentOrders(startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/insertOrder")
    @ResponseBody
    public Integer insertOrder(String oid, Integer user_id, String useraddress, String otime, String oprice, Integer shippingpay) {
        return orderService.insertOrder(oid, user_id, useraddress, otime, oprice, shippingpay);
    }

    @RequestMapping("/changeOpay")
    @ResponseBody
    public Integer changeOpay(String oid) {
        return orderService.changeOpay(oid);
    }

    @RequestMapping("/botainOrdersAll")
    @ResponseBody
    public Map<String, Object> botainOrdersAll(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryAllOrders(user_id);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainOrdersPay")
    @ResponseBody
    public Map<String, Object> botainOrdersPay(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryPayOrders(user_id);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainOrdersShipping")
    @ResponseBody
    public Map<String, Object> botainOrdersShipping(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryShippingOrders(user_id);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainOrdersSignfor")
    @ResponseBody
    public Map<String, Object> botainOrdersSignfor(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.querySignforOrders(user_id);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainOrdersComment")
    @ResponseBody
    public Map<String, Object> botainOrdersComment(Integer user_id){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCommentOrders(user_id);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/deleteOrderByOid")
    @ResponseBody
    public Integer deleteOrderByOid(String oid) {
        return orderService.deleteByOid(oid);
    }

    @RequestMapping("/changeOcomment")
    @ResponseBody
    public Integer changeOcomment(String oid) {
        return orderService.changeOcomment(oid);
    }

    @RequestMapping("/changeOshipping")
    @ResponseBody
    public Integer changeOshipping(String oid) {
        return orderService.changeOshipping(oid);
    }

    @RequestMapping("/changeOsignfor")
    @ResponseBody
    public Integer changeOsignfor(String oid) {
        return orderService.changeOsignfor(oid);
    }

    @RequestMapping("/obotainAllOrderCount")
    @ResponseBody
    public Integer obotainAllOrderCount(Integer user_id) {
        return orderService.queryAllOrderCounts(user_id);
    }

    @RequestMapping("/obotainPayOrderCount")
    @ResponseBody
    public Integer obotainPayOrderCount(Integer user_id) {
        return orderService.queryPayOrderCounts(user_id);
    }

    @RequestMapping("/obotainShippingOrderCount")
    @ResponseBody
    public Integer obotainShippingOrderCount(Integer user_id) {
        return orderService.queryShippingOrderCounts(user_id);
    }

    @RequestMapping("/obotainSignforOrderCount")
    @ResponseBody
    public Integer obotainSignforOrderCount(Integer user_id) {
        return orderService.querySignforOrderCounts(user_id);
    }

    @RequestMapping("/obotainCommentOrderCount")
    @ResponseBody
    public Integer obotainCommentOrderCount(Integer user_id) {
        return orderService.queryCommentOrderCounts(user_id);
    }

    @RequestMapping("/obtainCurrentAllOrder")
    @ResponseBody
    public Map<String, Object> obtainCurrentAllOrder(Integer user_id, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCurrentAllOrder(user_id, startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentPayOrder")
    @ResponseBody
    public Map<String, Object> obtainCurrentPayOrder(Integer user_id, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCurrentPayOrder(user_id, startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentShippingOrder")
    @ResponseBody
    public Map<String, Object> obtainCurrentShippingOrder(Integer user_id, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCurrentShippingOrder(user_id, startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentSignforOrder")
    @ResponseBody
    public Map<String, Object> obtainCurrentSignforOrder(Integer user_id, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCurrentSignforOrder(user_id, startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentCommentOrder")
    @ResponseBody
    public Map<String, Object> obtainCurrentCommentOrder(Integer user_id, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryCurrentCommentOrder(user_id, startIndex, pageSize);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/changeUserAddress")
    @ResponseBody
    public Integer changeUserAddress(String oid, String useraddress) {
        return orderService.changeAddress(oid, useraddress);
    }

    @RequestMapping("/obtainLikeByOid")
    @ResponseBody
    public Map<String, Object> obtainLikeByOid(String oidSnippet){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Order> orders = orderService.queryLikeByOid(oidSnippet);
        map.put("orders",orders);
        System.out.println(map);
        return map;
    }

}

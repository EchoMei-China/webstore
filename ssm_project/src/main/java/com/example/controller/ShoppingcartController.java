package com.example.controller;

import com.example.pojo.Shoppingcart;
import com.example.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingcartController {

    @Autowired
    private ShoppingcartService shoppingcartService;

    @RequestMapping("/updateQuantity")
    @ResponseBody
    public Integer updateQuantity(Integer sid, Integer quantity) {
        return shoppingcartService.updateQuantity(sid, quantity);
    }

    @RequestMapping("/deleteShoppingcartBySid")
    @ResponseBody
    public Integer deleteShoppingcartBySid(Integer sid) {
        return shoppingcartService.deleteShoppingcartBySid(sid);
    }

    @RequestMapping("/deleteAllByUserNickname")
    @ResponseBody
    public Integer deleteAllByUserNickname(Integer user_id) {
        return shoppingcartService.deleteAllByUserNickname(user_id);
    }

    @RequestMapping("/obtainByUsernickname")
    @ResponseBody
    public Map<String, Object> obtainByUsernickname(String user_nickname){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Shoppingcart> shoppingcarts = shoppingcartService.queryByUserNickname(user_nickname);
        map.put("shoppingcarts",shoppingcarts);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/insertShoppingcart")
    @ResponseBody
    public Integer insertShoppingcart(Integer user_id, Integer bid, Integer quantity) {
        return shoppingcartService.insertShoppingcart(user_id, bid, quantity);
    }

    @RequestMapping("/judgeShoppingcart")
    @ResponseBody
    public Integer judgeShoppingcart(Integer user_id, Integer bid) {
        return shoppingcartService.judgeShoppingcart(user_id, bid);
    }

    @RequestMapping("/updateShoppingcart")
    @ResponseBody
    public Integer updateShoppingcart(Integer user_id, Integer bid, Integer quantity) {
        return shoppingcartService.updateShoppingcart(user_id, bid, quantity);
    }

    @RequestMapping("/obtainBySid")
    @ResponseBody
    public Map<String, Object> obtainBySid(Integer sid){
        Map<String, Object> map = new HashMap<String, Object>();
        Shoppingcart shoppingcart = shoppingcartService.queryBySid(sid);
        map.put("shoppingcart",shoppingcart);
        System.out.println(map);
        return map;
    }
}

package com.example.service.impl;

import com.example.mapper.ShoppingcartMapper;
import com.example.pojo.Shoppingcart;
import com.example.service.ShoppingcartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingcartServiceImpl implements ShoppingcartService {

    @Autowired
    private ShoppingcartMapper mapper;

    @Override
    public Integer updateQuantity(@Param("sid") Integer sid, @Param("quantity") Integer quantity) {
        return mapper.updateQuantity(sid, quantity);
    }

    @Override
    public List<Shoppingcart> queryByUserNickname(@Param("user_nickname") String user_nickname) {
        return mapper.queryByUserNickname(user_nickname);
    }

    @Override
    public Integer deleteShoppingcartBySid(@Param("sid") Integer sid) {
        return mapper.deleteShoppingcartBySid(sid);
    }

    @Override
    public Integer deleteAllByUserNickname(@Param("user_id") Integer user_id) {
        return mapper.deleteAllByUserNickname(user_id);
    }

    @Override
    public Integer insertShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid, @Param("quantity") Integer quantity) {
        return mapper.insertShoppingcart(user_id, bid, quantity);
    }

    @Override
    public Integer judgeShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid) {
        return mapper.judgeShoppingcart(user_id, bid);
    }

    @Override
    public Integer updateShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid, @Param("quantity") Integer quantity) {
        return mapper.updateShoppingcart(user_id, bid, quantity);
    }

    @Override
    public Shoppingcart queryBySid(@Param("sid") Integer sid) {
        return mapper.queryBySid(sid);
    }
}

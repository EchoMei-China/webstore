package com.example.mapper;

import com.example.pojo.Shoppingcart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoppingcartMapper {
    /**
     * 根据sid找到记录，然后修改quantity
     */
    Integer updateQuantity(@Param("sid") Integer sid, @Param("quantity") Integer quantity);

    /**
     * 根据用户名取出对应的购物车记录
     */
    List<Shoppingcart> queryByUserNickname(@Param("user_nickname") String user_nickname);

    /**
     * 根据sid删除shoppingcart记录
     */
    Integer deleteShoppingcartBySid(@Param("sid") Integer sid);

    /**
     * 根据用户user_id删除所有shoppingcart记录
     */
    Integer deleteAllByUserNickname(@Param("user_id") Integer user_id);

    /**
     * 加入购物车
     */
    Integer insertShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid, @Param("quantity") Integer quantity);

    /**
     * 根据用户id和书id来判断表中是否已经有这个商品
     */
    Integer judgeShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid);

    /**
     * 更新商品数量
     */
    Integer updateShoppingcart(@Param("user_id") Integer user_id, @Param("bid") Integer bid, @Param("quantity") Integer quantity);

    /**
     * 根据sid，获取记录
     */
    Shoppingcart queryBySid(@Param("sid") Integer sid);
}

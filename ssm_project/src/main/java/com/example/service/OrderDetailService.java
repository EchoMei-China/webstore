package com.example.service;

import com.example.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailService {

    /**
     * 插入orderdetail
     */
    Integer insertOrderDetail(@Param("oid") String oid, @Param("bid") Integer bid, @Param("odquantity") Integer odquantity, @Param("odprice") String odprice);

    /**
     * 根据oid获取orderdetail
     */
    List<OrderDetail> queryAllOrderdetails(@Param("oid") String oid);

    /**
     * 根据oid删除
     */
    Integer deleteByOid(@Param("oid") String oid);
}

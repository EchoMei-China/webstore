package com.example.mapper;

import com.example.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    /**
     * 根据bid查询所有评论
     */
    List<Comment> queryCommentByBid(@Param("cbid") Integer paramater);

    /**
     * 获取总记录数
     */
    int queryCount(@Param("cbid") Integer paramater);

    /**
     * 根据当前的页码和页面容量来查询记录
     */
    List<Comment> queryCurrentCount(@Param("cbid") Integer cbid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 插入评论
     */
    int insertComment(@Param("bid") Integer bid, @Param("ccontent") String ccontent, @Param("ctime") String ctime);
}

package com.example.service.impl;

import com.example.mapper.CommentMapper;
import com.example.pojo.Comment;
import com.example.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper mapper;

    @Override
    public List<Comment> queryCommentByBid(@Param("cbid") Integer paramater) {
        return mapper.queryCommentByBid(paramater);
    }

    @Override
    public int queryCount(@Param("cbid") Integer paramater) {
        return mapper.queryCount(paramater);
    }

    @Override
    public List<Comment> queryCurrentCount(@Param("cbid") Integer cbid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentCount(cbid, startIndex, pageSize);
    }

    @Override
    public int insertComment(@Param("bid") Integer bid, @Param("ccontent") String ccontent, @Param("ctime") String ctime) {
        return mapper.insertComment(bid, ccontent, ctime);
    }
}

package com.example.controller;

import com.example.pojo.Comment;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/botainCommentByBid")
    @ResponseBody
    public Map<String, Object> botainCommentByBid(Integer cbid){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Comment> comments = commentService.queryCommentByBid(cbid);
        map.put("comments",comments);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/botainTotal")
    @ResponseBody
    public int botainTotal(Integer cbid) {
        int total = commentService.queryCount(cbid);
        return total;
    }

    @RequestMapping("/obtainCurrentContent")
    @ResponseBody
    public Map<String, Object> obtainCurrentContent(Integer cbid, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Comment> comments = commentService.queryCurrentCount(cbid, startIndex, pageSize);
        map.put("comments",comments);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/insertComment")
    @ResponseBody
    public Integer insertComment(Integer bid, String ccontent, String ctime) {
        return commentService.insertComment(bid, ccontent, ctime);
    }
}

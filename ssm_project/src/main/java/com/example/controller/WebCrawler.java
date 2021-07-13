package com.example.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/WebCrawler")
public class WebCrawler {
    @RequestMapping(value = "/getTrialReading1")
    @ResponseBody
    //选择内容爬下来
//    public Map<String, Object> getMoDu1() throws IOException {
//        String url ="http://www.loubiqu.com/52_52447/6363452.html";
//        Map<String, Object> map = new HashMap<String, Object>();
//        Document doc = Jsoup.connect(url).get();
//        //得到html中id为content下的所有内容
//        Element ele = doc.getElementById("content");
//        //分离出下面的具体内容
//        Elements tag = ele.getElementsByTag("div");
//        String text = tag.text();
//        System.out.println(text);
//        map.put("text",text);
//        return map;
//    }
        public Map<String, Object> getTrialReading1(String http) throws IOException {
            Map<String, Object> map = new HashMap<String, Object>();
            Document doc = Jsoup.connect(http).get();
            //得到html中id为content下的所有内容
            Element ele = doc.getElementById("content");
            //分离出下面的具体内容
            Elements tag = ele.getElementsByTag("div");
            String text = tag.text();
            //System.out.println(text);
            map.put("text",text);
            return map;
        }
}

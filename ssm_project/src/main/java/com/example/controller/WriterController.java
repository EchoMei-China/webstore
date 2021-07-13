package com.example.controller;

import com.example.pojo.Writer;
import com.example.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @RequestMapping("/botainAboutAuthor")
    @ResponseBody
    public Map<String, Object> botainAboutAuthor(String wname){
        Map<String, Object> map = new HashMap<String, Object>();
        Writer writer = writerService.queryByBwriter(wname);
        map.put("writer",writer);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtianAllWriters")
    @ResponseBody
    public Map<String, Object> obtianAllWriters(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Writer> writers = writerService.queryAllWriters();
        map.put("writers",writers);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtianAllWritersCount")
    @ResponseBody
    public Integer obtianAllWritersCount() {
        return writerService.queryAllWritersCount();
    }

    @RequestMapping("/ManageCurrentWriters")
    @ResponseBody
    public Map<String, Object> ManageCurrentWriters(Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Writer> writers = writerService.queryCurrentWriters(startIndex, pageSize);
        map.put("writers",writers);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/insertNewWriter")
    @ResponseBody
    public Integer insertNewWriter(String bwriter, String wintroduction, String wmasterpiece) {
        return writerService.insertNewWriter(bwriter, wintroduction, wmasterpiece);
    }

    @RequestMapping("/updateWriterByWid")
    @ResponseBody
    public Integer updateWriterByWid(Integer wid, String wintroduction, String wmasterpiece) {
        return writerService.updateWriterByWid(wid, wintroduction, wmasterpiece);
    }

    @RequestMapping("/obtainWriterBysearch")
    @ResponseBody
    public Map<String, Object> obtainWriterBysearch(String searchInformation){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Writer> writers = writerService.searchWriter(searchInformation);
        map.put("writers",writers);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainWriterByWid")
    @ResponseBody
    public Map<String, Object> obtainWriterByWid(Integer wid){
        Map<String, Object> map = new HashMap<String, Object>();
        Writer writer = writerService.queryWriterByWid(wid);
        map.put("writer",writer);
        System.out.println(map);
        return map;
    }
}

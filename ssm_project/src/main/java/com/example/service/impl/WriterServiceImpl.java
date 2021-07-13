package com.example.service.impl;

import com.example.mapper.WriterMapper;
import com.example.pojo.Writer;
import com.example.service.WriterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterMapper mapper;

    @Override
    public Writer queryByBwriter(@Param("wname") String paramater) {
        return mapper.queryByBwriter(paramater);
    }

    @Override
    public List<Writer> queryAllWriters() {
        return mapper.queryAllWriters();
    }

    @Override
    public int queryAllWritersCount() {
        return mapper.queryAllWritersCount();
    }

    @Override
    public List<Writer> queryCurrentWriters(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentWriters(startIndex, pageSize);
    }

    @Override
    public int insertNewWriter(@Param("bwriter") String bwriter, @Param("wintroduction") String wintroduction, @Param("wmasterpiece") String wmasterpiece) {
        return mapper.insertNewWriter(bwriter, wintroduction, wmasterpiece);
    }

    @Override
    public int updateWriterByWid(@Param("wid") Integer wid, @Param("wintroduction") String wintroduction, @Param("wmasterpiece") String wmasterpiece) {
        return mapper.updateWriterByWid(wid, wintroduction, wmasterpiece);
    }

    @Override
    public List<Writer> searchWriter(@Param("searchInformation") String searchInformation) {
        return mapper.searchWriter(searchInformation);
    }

    @Override
    public Writer queryWriterByWid(@Param("wid") Integer wid) {
        return mapper.queryWriterByWid(wid);
    }

}

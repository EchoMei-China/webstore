package com.example.service.impl;

import com.example.mapper.BookTypeMapper;
import com.example.pojo.BookType;
import com.example.service.BookTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeMapper mapper;

    @Override
    public List<BookType> queryByBookKind (@Param("kid") Integer parameter) {
        return mapper.queryByBookKind(parameter);
    }

    @Override
    public BookType queryByTid (@Param("btid") Integer parameter) {
        return mapper.queryByTid(parameter);
    }

    @Override
    public BookType queryByTname(@Param("tname") String tname) {
        return mapper.queryByTname(tname);
    }
}

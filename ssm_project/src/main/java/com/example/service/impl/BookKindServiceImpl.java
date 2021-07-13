package com.example.service.impl;


import com.example.mapper.BookKindMapper;
import com.example.pojo.BookKind;
import com.example.service.BookKindService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookKindServiceImpl implements BookKindService {

    @Autowired
    private BookKindMapper mapper;

    @Override
    public List<BookKind> queryBookKind(){
        return mapper.queryBookKind();
    }

    @Override
    public BookKind queryByKid (@Param("tkid") Integer parameter) {
        return mapper.queryByKid(parameter);
    }

    @Override
    public BookKind queryByKname (@Param("kname") String kname) {
        return mapper.queryByKname(kname);
    }
}

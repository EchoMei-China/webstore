package com.example.service;

import com.example.pojo.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookTypeService {

    List<BookType> queryByBookKind (@Param("kid") Integer parameter);

    /**
     * 根据tid查询tname
     */
    BookType queryByTid (@Param("btid") Integer parameter);

    /**
     * 根据tname查询tid
     */
    BookType queryByTname(@Param("tname") String tname);
}

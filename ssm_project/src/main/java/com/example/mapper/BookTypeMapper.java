package com.example.mapper;

import com.example.pojo.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookTypeMapper {

    /**
     *根据kid查询tname
    **/
    List<BookType> queryByBookKind (@Param("tkid") Integer parameter);

    /**
     * 根据tid查询tname
     */
    BookType queryByTid (@Param("btid") Integer parameter);

    /**
     * 根据tname查询tid
     */
    BookType queryByTname(@Param("tname") String tname);
}

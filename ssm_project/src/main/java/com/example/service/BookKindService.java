package com.example.service;


import com.example.pojo.BookKind;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookKindService {

     List<BookKind> queryBookKind();

     /**
      * 根据kid查询kname
      */
     BookKind queryByKid (@Param("tkid") Integer parameter);

     /**
      * 根据kname查询kid
      */
     BookKind queryByKname (@Param("kname") String kname);
}

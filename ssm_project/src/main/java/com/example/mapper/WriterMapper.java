package com.example.mapper;

import com.example.pojo.Writer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WriterMapper {

    /**
     * 根据bwriter查询 1条数据
     */
    Writer queryByBwriter(@Param("wname") String parameter);

    /**
     * 获取全部记录
     */
    List<Writer> queryAllWriters();

    /**
     * 获取总记录数
     */
    int queryAllWritersCount();

    /**
     * 分页
     */
    List<Writer> queryCurrentWriters(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 新增
     */
    int insertNewWriter(@Param("bwriter") String bwriter, @Param("wintroduction") String wintroduction, @Param("wmasterpiece") String wmasterpiece);

    /**
     * 根据wid修改
     */
    int updateWriterByWid(@Param("wid") Integer wid, @Param("wintroduction") String wintroduction, @Param("wmasterpiece") String wmasterpiece);

    /**
     * 根据搜索获取
     */
    List<Writer> searchWriter(@Param("searchInformation") String searchInformation);

    /**
     * 根据wid获取
     */
    Writer queryWriterByWid(@Param("wid") Integer wid);
}

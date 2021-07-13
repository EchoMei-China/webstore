package com.example.mapper;

import com.example.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    /**
     * 获取allbooks
     */
    List<Book> queryAllBooks();

    /**
     * 获取数据总数
     */
    int queryAllBooksCount();

    /**
     * 所有books分页
     */
    List<Book> queryCurrentAllBooks(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据bid获取图书
     */
    Book queryBookByBid(@Param("bid") Integer bid);

    /**
     * 根据点击数量clicknum降序排列，选取点击最多的5个 放在热书推荐里
     */
    List<Book> queryFivePopularBook();

    /**
     * 根据库存binventory不为0 升序排列，选取库存数量最少的5个，放在少量现货中
     */
    List<Book> queryFiveSmallSpotBook();

    /**
     * 库存binventory为0，点击数量clicknum降序排列，选取点击最多的库存数量为0的5个，放在预购从速中
     */
    List<Book> queryFiveSoonScheduled();

    /**
     * 根据tid，获取书籍，显示在list中【取最受欢迎的前12本，即clicknum降序排序前12本】
     */
    List<Book> queryBookByTid(@Param("btid") Integer parameter);

    /**
     * 根据favorites降序排序，获取最多收藏的书籍，【取favorites降序，前5本】
     */
    List<Book> queryFiveFavoritesBook();

    /**
     * 根据kid，查询所有书籍，根据clicknum降序排序，取前12本
     */
    List<Book> query12BooksByKid(@Param("kid") Integer kid);

    /**
     * 根据kid，获取总的记录数
     */
    int queryCount(@Param("kid") Integer kid);

    /**
     * 根据tid，获取总记录数
     */
    int queryCountByTid(@Param("btid") Integer btid);

    /**
     * 根据当前的页码和页面容量来查询记录
     */
    List<Book> queryCurrentCount(@Param("kid") Integer kid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据搜索内容查找书本
     */
    List<Book> queryBySearch(@Param("searchInformation") String searchInformation);

    /**
     * tid分页
     */
    List<Book> queryCurrentCountByTid(@Param("tid") Integer tid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据search获取总记录数
     */
    int queryCountBySearch(@Param("searchInformation") String searchInformation);

    /**
     * 根据搜索内容分页
     */
    List<Book> queryCurrentCountBySearch(@Param("searchInformation") String searchInformation);

    /**
     * 根据bid删除书本
     */
    int deleteByBid(@Param("bid") Integer bid);

    /**
     * 增加clicknum
     */
    int addClicknum(@Param("bid") Integer bid);

    /**
     * 增加favorites
     */
    int addFavorites(@Param("bid") Integer bid);

    /**
     * 根据bid和quantity减少库存量
     */
    int reduceBinventory(@Param("bid") Integer bid, @Param("odquantity") Integer odquantity);

    /**
     * 根据bid和quantity增加销售量
     */
    int addSalenum(@Param("bid") Integer bid, @Param("odquantity") Integer odquantity);

    /**
     * 新增图书
     */
    int addBook(@Param("bname") String bname, @Param("bnowprice") Double bnowprice, @Param("bprice") Double bprice, @Param("bwriter") String bwriter, @Param("btranslate") String btranslate, @Param("bpublishing") String bpublishing, @Param("binventory") Integer binventory, @Param("bisbn") String bisbn, @Param("bsuit") String bsuit, @Param("bimage") String bimage);
}

package com.example.service.impl;

import com.example.mapper.BookMapper;
import com.example.pojo.Book;
import com.example.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper mapper;

    @Override
    public List<Book> queryAllBooks() {
        return mapper.queryAllBooks();
    }

    @Override
    public int queryAllBooksCount() {
        return mapper.queryAllBooksCount();
    }

    @Override
    public List<Book> queryCurrentAllBooks(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentAllBooks(startIndex, pageSize);
    }

    @Override
    public int deleteByBid(@Param("bid") Integer bid) {
        return mapper.deleteByBid(bid);
    }

    @Override
    public Book queryBookByBid(@Param("bid") Integer bid) {
        return mapper.queryBookByBid(bid);
    }

    @Override
    public List<Book> queryFivePopularBook() {
        return mapper.queryFivePopularBook();
    }

    @Override
    public List<Book> queryFiveSmallSpotBook() {
        return mapper.queryFiveSmallSpotBook();
    }

    @Override
    public List<Book> queryFiveSoonScheduled() {
        return mapper.queryFiveSoonScheduled();
    }

    @Override
    public List<Book> queryBookByTid(@Param("btid") Integer parameter) {
        return mapper.queryBookByTid(parameter);
    }

    @Override
    public List<Book> queryFiveFavoritesBook() {
        return mapper.queryFiveFavoritesBook();
    }

    @Override
    public List<Book> query12BooksByKid(@Param("kid") Integer kid) {
        return mapper.query12BooksByKid(kid);
    }

    @Override
    public int queryCount(@Param("kid") Integer kid) {
        return mapper.queryCount(kid);
    }

    @Override
    public List<Book> queryCurrentCount(@Param("kid") Integer kid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentCount(kid, startIndex, pageSize);
    }

    @Override
    public List<Book> queryBySearch(@Param("searchInformation") String searchInformation) {
        return mapper.queryBySearch(searchInformation);
    }

    @Override
    public int queryCountByTid(@Param("btid") Integer btid) {
        return mapper.queryCountByTid(btid);
    }

    @Override
    public List<Book> queryCurrentCountByTid(@Param("tid") Integer tid, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize) {
        return mapper.queryCurrentCountByTid(tid, startIndex, pageSize);
    }

    @Override
    public int queryCountBySearch(@Param("searchInformation") String searchInformation) {
        return mapper.queryCountBySearch(searchInformation);
    }

    @Override
    public List<Book> queryCurrentCountBySearch(@Param("searchInformation") String searchInformation) {
        return mapper.queryCurrentCountBySearch(searchInformation);
    }

    @Override
    public int addClicknum(@Param("bid") Integer bid) {
        return mapper.addClicknum(bid);
    }

    @Override
    public int addFavorites(@Param("bid") Integer bid) {
        return mapper.addFavorites(bid);
    }

    @Override
    public int reduceBinventory(@Param("bid") Integer bid, @Param("odquantity") Integer odquantity) {
        return mapper.reduceBinventory(bid, odquantity);
    }

    @Override
    public int addSalenum(@Param("bid") Integer bid, @Param("odquantity") Integer odquantity) {
        return mapper.addSalenum(bid, odquantity);
    }

    @Override
    public int addBook(@Param("bname") String bname, @Param("bnowprice") Double bnowprice, @Param("bprice") Double bprice, @Param("bwriter") String bwriter, @Param("btranslate") String btranslate, @Param("bpublishing") String bpublishing, @Param("binventory") Integer binventory, @Param("bisbn") String bisbn, @Param("bsuit") String bsuit, @Param("bimage") String bimage) {
        return mapper.addBook(bname, bnowprice, bprice, bwriter, btranslate, bpublishing, binventory, bisbn, bsuit, bimage);
    }

}

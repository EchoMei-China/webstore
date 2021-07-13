package com.example.controller;


import com.example.pojo.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/obtainAllBooks")
    @ResponseBody
    public Map<String, Object> obtainAllBooks(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryAllBooks();
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/deleteBookByBid")
    @ResponseBody
    public Integer deleteBookByBid(Integer bid) {
        return bookService.deleteByBid(bid);
    }

    @RequestMapping("/obtainAllbooksCount")
    @ResponseBody
    public Integer obtainAllbooksCount() {
        return bookService.queryAllBooksCount();
    }

    @RequestMapping("/ManageCurrentContentBooks")
    @ResponseBody
    public Map<String, Object> ManageCurrentContentBooks(Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryCurrentAllBooks(startIndex, pageSize);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBookByBid")
    @ResponseBody
    public Map<String, Object> obtainBookByBid(Integer bid){
        Map<String, Object> map = new HashMap<String, Object>();
        Book book = bookService.queryBookByBid(bid);
        map.put("book",book);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainFivePopularBook")
    @ResponseBody
    public Map<String, Object> obtainFivePopularBook(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryFivePopularBook();
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainFiveSmallSpotBook")
    @ResponseBody
    public Map<String, Object> obtainFiveSmallSpotBook(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryFiveSmallSpotBook();
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainFiveSoonScheduled")
    @ResponseBody
    public Map<String, Object> obtainFiveSoonScheduled(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryFiveSoonScheduled();
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBookList")
    @ResponseBody
    public Map<String, Object> obtainBookList(Integer btid){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryBookByTid(btid);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainFiveFavoritesBook")
    @ResponseBody
    public Map<String, Object> obtainFiveFavoritesBook(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryFiveFavoritesBook();
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBookByKid")
    @ResponseBody
    public Map<String, Object> obtainBookByKid(Integer kid){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.query12BooksByKid(kid);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainTotalBooks")
    @ResponseBody
    public int obtainTotalBooks(Integer kid) {
        int total = bookService.queryCount(kid);
        return total;
    }

    @RequestMapping("/obtainTotalBooksByTid")
    @ResponseBody
    public int obtainTotalBooksByTid(Integer btid) {
        return bookService.queryCountByTid(btid);
    }

    @RequestMapping("/obtainTotalBooksBySearch")
    @ResponseBody
    public int obtainTotalBooksBySearch(String searchInformation) {
        return bookService.queryCountBySearch(searchInformation);
    }

    @RequestMapping("/obtainCurrentContent")
    @ResponseBody
    public Map<String, Object> obtainCurrentContent(Integer kid, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryCurrentCount(kid, startIndex, pageSize);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentContentByTid")
    @ResponseBody
    public Map<String, Object> obtainCurrentContentByTid(Integer tid, Integer startIndex, Integer pageSize){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryCurrentCountByTid(tid, startIndex, pageSize);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainBooksBySearch")
    @ResponseBody
    public Map<String, Object> obtainBooksBySearch(String searchInformation){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryBySearch(searchInformation);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/obtainCurrentContentBySearch")
    @ResponseBody
    public Map<String, Object> obtainCurrentContentBySearch(String searchInformation){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Book> books = bookService.queryCurrentCountBySearch(searchInformation);
        map.put("books",books);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addClicknum")
    @ResponseBody
    public Integer addClicknum(Integer bid) {
        return bookService.addClicknum(bid);
    }

    @RequestMapping("/addFavorites")
    @ResponseBody
    public Integer addFavorites(Integer bid) {
        return bookService.addFavorites(bid);
    }

    @RequestMapping("/reduceBinventory")
    @ResponseBody
    public Integer reduceBinventory(Integer bid, Integer odquantity) {
        return bookService.reduceBinventory(bid, odquantity);
    }

    @RequestMapping("/addSalenum")
    @ResponseBody
    public Integer addSalenum(Integer bid, Integer odquantity) {
        return bookService.addSalenum(bid, odquantity);
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public Integer addBook(String bname, Double bnowprice, Double bprice, String bwriter, String btranslate, String bpublishing, Integer binventory, String bisbn, String bsuit, String bimage) {
        return bookService.addBook(bname, bnowprice, bprice, bwriter, btranslate, bpublishing, binventory, bisbn, bsuit, bimage);
    }
}

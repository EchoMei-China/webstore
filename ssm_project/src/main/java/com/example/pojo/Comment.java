package com.example.pojo;

public class Comment {
    private Integer cid;
    private Integer bid;
    private String ccontent;
    private String ctime;
    private Book book;

    public Book getBook() {
        return book;
    }

    public Integer getCid() {
        return cid;
    }

    public Integer getBid() {
        return bid;
    }

    public String getCcontent() {
        return ccontent;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

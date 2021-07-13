package com.example.pojo;

public class Writer {
    private Integer wid;
    private String bwriter;
    private String wintroduction;
    private String wmasterpiece;
    private Book book;

    public Book getBook() {
        return book;
    }

    public Integer getWid() {
        return wid;
    }

    public String getBwriter() {
        return bwriter;
    }

    public String getWintroduction() {
        return wintroduction;
    }

    public String getWmasterpiece() {
        return wmasterpiece;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    public void setWintroduction(String wintroduction) {
        this.wintroduction = wintroduction;
    }

    public void setWmasterpiece(String wmasterpiece) {
        this.wmasterpiece = wmasterpiece;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

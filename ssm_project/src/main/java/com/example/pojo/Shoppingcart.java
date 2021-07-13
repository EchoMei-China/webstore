package com.example.pojo;

public class Shoppingcart {
    private Integer sid;
    private Integer user_id;
    private Integer bid;
    private Integer quantity;
    private  User user;
    private Book book;

    public Integer getSid() {
        return sid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getBid() {
        return bid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

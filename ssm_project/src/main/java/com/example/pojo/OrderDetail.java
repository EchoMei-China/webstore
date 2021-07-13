package com.example.pojo;

public class OrderDetail {
    private Integer odid;
    private String oid;
    private Order order;
    private Integer bid;
    private Book book;
    private Integer odquantity;
    private String odprice;

    public Integer getOdid() {
        return odid;
    }

    public String getOid() {
        return oid;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getBid() {
        return bid;
    }

    public Book getBook() {
        return book;
    }

    public Integer getOdquantity() {
        return odquantity;
    }

    public String getOdprice() {
        return odprice;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setOdquantity(Integer odquantity) {
        this.odquantity = odquantity;
    }

    public void setOdprice(String odprice) {
        this.odprice = odprice;
    }
}

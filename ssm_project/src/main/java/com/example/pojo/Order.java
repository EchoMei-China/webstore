package com.example.pojo;

public class Order {
    private String oid;
    private Integer user_id;
    private User user;
    private String useraddress;
    private Integer opay;
    private Integer oshipping;
    private Integer osignfor;
    private Integer ocomment;
    private String otime;
    private String oprice;
    private Integer shippingpay;

    public Integer getOcomment() {
        return ocomment;
    }

    public void setOcomment(Integer ocomment) {
        this.ocomment = ocomment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOid() {
        return oid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public Integer getOpay() {
        return opay;
    }

    public Integer getOshipping() {
        return oshipping;
    }

    public Integer getOsignfor() {
        return osignfor;
    }

    public String getOtime() {
        return otime;
    }

    public String getOprice() {
        return oprice;
    }

    public Integer getShippingpay() {
        return shippingpay;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public void setOpay(Integer opay) {
        this.opay = opay;
    }

    public void setOshipping(Integer oshipping) {
        this.oshipping = oshipping;
    }

    public void setOsignfor(Integer osignfor) {
        this.osignfor = osignfor;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public void setShippingpay(Integer shippingpay) {
        this.shippingpay = shippingpay;
    }
}

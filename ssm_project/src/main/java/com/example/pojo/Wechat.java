package com.example.pojo;

public class Wechat {
    private Integer wcid;
    private Integer user_id;
    private User user;
    private String wcnum;
    private String wcpsd;
    private Integer wcpaypsd;

    public Integer getWcid() {
        return wcid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public User getUser() {
        return user;
    }

    public String getWcnum() {
        return wcnum;
    }

    public String getWcpsd() {
        return wcpsd;
    }

    public Integer getWcpaypsd() {
        return wcpaypsd;
    }

    public void setWcid(Integer wcid) {
        this.wcid = wcid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWcnum(String wcnum) {
        this.wcnum = wcnum;
    }

    public void setWcpsd(String wcpsd) {
        this.wcpsd = wcpsd;
    }

    public void setWcpaypsd(Integer wcpaypsd) {
        this.wcpaypsd = wcpaypsd;
    }
}

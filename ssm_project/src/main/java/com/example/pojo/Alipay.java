package com.example.pojo;

public class Alipay {
    private Integer apid;
    private Integer user_id;
    private User user;
    private String apnum;
    private String appsd;
    private Integer appaypsd;

    public Integer getApid() {
        return apid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public User getUser() {
        return user;
    }

    public String getApnum() {
        return apnum;
    }

    public String getAppsd() {
        return appsd;
    }

    public Integer getAppaypsd() {
        return appaypsd;
    }

    public void setApid(Integer apid) {
        this.apid = apid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApnum(String apnum) {
        this.apnum = apnum;
    }

    public void setAppsd(String appsd) {
        this.appsd = appsd;
    }

    public void setAppaypsd(Integer appaypsd) {
        this.appaypsd = appaypsd;
    }
}

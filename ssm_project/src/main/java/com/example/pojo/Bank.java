package com.example.pojo;

public class Bank {
    private Integer bkid;
    private String bkname;
    private String bkimage;
    private String singlelimit;
    private String dailylimit;

    public Integer getBkid() {
        return bkid;
    }

    public String getBkname() {
        return bkname;
    }

    public String getBkimage() {
        return bkimage;
    }

    public String getSinglelimit() {
        return singlelimit;
    }

    public String getDailylimit() {
        return dailylimit;
    }

    public void setBkid(Integer bkid) {
        this.bkid = bkid;
    }

    public void setBkname(String bkname) {
        this.bkname = bkname;
    }

    public void setBkimage(String bkimage) {
        this.bkimage = bkimage;
    }

    public void setSinglelimit(String singlelimit) {
        this.singlelimit = singlelimit;
    }

    public void setDailylimit(String dailylimit) {
        this.dailylimit = dailylimit;
    }
}

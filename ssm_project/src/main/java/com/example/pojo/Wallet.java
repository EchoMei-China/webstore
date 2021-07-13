package com.example.pojo;

public class Wallet {
    private Integer wid;
    private Integer user_id;
    private Integer bkid;
    private String cardtype;
    private String cardnumber;
    private String cardpsd;
    private User user;
    private Bank bank;

    public Integer getWid() {
        return wid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getBkid() {
        return bkid;
    }

    public String getCardtype() {
        return cardtype;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public User getUser() {
        return user;
    }

    public Bank getBank() {
        return bank;
    }

    public String getCardpsd() {
        return cardpsd;
    }

    public void setCardpsd(String cardpsd) {
        this.cardpsd = cardpsd;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setBkid(Integer bkid) {
        this.bkid = bkid;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}

package com.example.pojo;

public class User {
    private String userId;//用户id
    private String cardType;//证件类型
    private String cardNo;//证件号码
    private String userName;//用户真名
    private String userNickname;//用户名
    private String userPassword;//用户密码
    private String userPhone;//用户手机号
    private String userSex;//用户性别
    private String userAge;//用户年龄
    private String userRole;//用户角色
    private Double change;
    private Double integral;

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
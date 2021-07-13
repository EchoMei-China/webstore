package com.example.pojo;

public class Address {
    private Integer adid;
    private Integer user_id;
    private String uname;
    private String uphone;
    private String pname;
    private String cname;
    private String dname;
    private String area;
    private String postalcode;
    private User user;
    private Province province;
    private City city;
    private District district;

    public Integer getAdid() {
        return adid;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUname() {
        return uname;
    }

    public String getUphone() {
        return uphone;
    }

    public String getPname() {
        return pname;
    }

    public String getCname() {
        return cname;
    }

    public String getDname() {
        return dname;
    }

    public String getArea() {
        return area;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public User getUser() {
        return user;
    }

    public Province getProvince() {
        return province;
    }

    public City getCity() {
        return city;
    }

    public District getDistrict() {
        return district;
    }

    public void setAdid(Integer adid) {
        this.adid = adid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}

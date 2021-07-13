package com.example.pojo;

public class District {
    private Integer did;
    private String dname;
    private Integer cid;
    private Integer shipping;
    private City city;

    public Integer getDid() {
        return did;
    }

    public String getDname() {
        return dname;
    }

    public Integer getCid() {
        return cid;
    }

    public Integer getShipping() {
        return shipping;
    }

    public City getCity() {
        return city;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

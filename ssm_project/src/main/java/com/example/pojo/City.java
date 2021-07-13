package com.example.pojo;

public class City {
    private Integer cid;
    private String cname;
    private Integer pid;
    private Province province;

    public Integer getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public Integer getPid() {
        return pid;
    }

    public Province getProvince() {
        return province;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}

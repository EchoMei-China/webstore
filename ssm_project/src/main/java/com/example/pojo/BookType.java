package com.example.pojo;

public class BookType {
    private Integer tid;
    private String tname;
    private Integer kid;
    private BookKind bookkind;

    public Integer getKid() {
        return kid;
    }

    public Integer getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }

    public BookKind getBookkind() {
        return bookkind;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setBookkind(BookKind bookkind) {
        this.bookkind = bookkind;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }
}

package com.xj.domain;

/**
 * Created by Asus on 2017/6/2.
 */
public class SystemDDL {
    private int id;
    private String keyword;
    private int ddlcode;
    private String ddlname;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setDdlcode(int ddlcode) {
        this.ddlcode = ddlcode;
    }

    public int getDdlcode() {
        return ddlcode;
    }

    public void setDdlname(String ddlname) {
        this.ddlname = ddlname;
    }

    public String getDdlname() {
        return ddlname;
    }

}

package com.xj.domain;

import java.util.Date;

/**
 * Created by zhengguo on 2017/6/17.
 */
public class Draft {
    private int id;
    private String title;
    private String note;
    private String content;
    private int user_id;
    private int category;
    private int second_category;
    private Date time;
    private int picture_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getSecond_category() {
        return second_category;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setSecond_category(int second_category) {
        this.second_category = second_category;
    }

    public String getContent() {
        return content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}


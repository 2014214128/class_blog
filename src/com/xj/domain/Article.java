package com.xj.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public class Article {
    private int id;
    private String title;
    private String content;
    private String note;
    private int user_id;
    private int picture_id = 0;
    private int category;
    private int status;
    private int part;
    private int second_category;
    private Picture picture;
    private Date publish_time;
    private User user;
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static final int STATUS_REVIEW = 0;   //审核中
    public static final int STATUS_PASS = 1;     //审核通过


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSecond_category() {
        return second_category;
    }

    public void setSecond_category(int second_category) {
        this.second_category = second_category;
    }


    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Picture getPicture() {
        return picture;
    }

}

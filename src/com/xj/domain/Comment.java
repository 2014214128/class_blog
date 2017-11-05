package com.xj.domain;

import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public class Comment {
    private int id ;
    private int article_id;
    private int user_id;
    private String content;
    private Date time;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

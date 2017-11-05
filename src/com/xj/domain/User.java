package com.xj.domain;

import java.util.Date;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public class User {
    private int id;
    private String name;
    private String password;
    private int sex;
    private String phone;
    private String email;
    private Date birthday;
    private String address;
    private int grade;   //用户等级
    private int status;  //用户注册状态
    private String loginname;
    private int picture_id;
    private String uuid;
    private Picture picture;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public boolean isNull() {
        return this instanceof USER_NULL;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public static final int SEX_MAN = 0;           //男
    public static final int SEX_WOMEN = 1;         //女
    public static final int STATUS_INACTIVED = 0;  //未激活状态
    public static final int STATUS_ACTIVE = 1;     //激活状态

    public static final User User_NULL = new USER_NULL();

    public static class USER_NULL extends User {
        public String getName() {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

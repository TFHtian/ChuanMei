package com.example.administrator.chuanmei.entity;

/**
 * Created by ASUS on 2017/2/21.
 */
public class UserInfo {
    private String username;
    private String sessionid;
    private int id;
    private String email;
    private String avatar;
    private String phone;

    public UserInfo() {
    }

    public UserInfo(String username, String sessionid, int id, String email, String avatar, String phone) {
        this.username = username;
        this.sessionid = sessionid;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

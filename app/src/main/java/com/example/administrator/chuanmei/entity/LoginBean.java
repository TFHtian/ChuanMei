package com.example.administrator.chuanmei.entity;

/**
 * Created by ASUS on 2017/2/21.
 */
public class LoginBean {
    private int status;
    private String message;
    private UserInfo result;

    public LoginBean() {
    }

    public LoginBean(int status, String message, UserInfo result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getResult() {
        return result;
    }

    public void setResult(UserInfo result) {
        this.result = result;
    }
}

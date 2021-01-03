package com.jishou.entity;


import java.util.Date;

public class User {
    private  Integer userId;
    private  String  user_account;
    private  String  user_password;
    private Date user_registerDate;
    public User() {}
    public User(int userId, String user_account, String user_password, Date user_registerDate) {
        this.userId = userId;
        this.user_account = user_account;
        this.user_password = user_password;
        this.user_registerDate = user_registerDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUser_account() {
        return user_account;
    }
    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public Date getUser_registerDate() {
        return user_registerDate;
    }
    public void setUser_registerDate(Date user_registerDate) {
        this.user_registerDate = user_registerDate;
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", user_account='" + user_account + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_registerDate=" + user_registerDate +
                '}';
    }
}

package com.seckill.seckill.entity;

import java.util.Date;

public class Token {
    
    private int id;
    private User user;
    private String token;
    private int status;
    private Date expired;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getExpired() {
        return expired;
    }
    public void setExpired(Date expired) {
        this.expired = expired;
    }
    @Override
    public String toString() {
        return "LoginToken [id=" + id + ", user=" + user + ", token=" + token + ", status=" + status
                + ", expired=" + expired + "]";
    }
    
}

package com.seckill.seckill.entity;

import java.util.Date;

public class Token {
    
    private int id;
    private int userId;
    private String token;
    private int status;
    private Date expired;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
        return "LoginToken [id=" + id + ", userId=" + userId + ", token=" + token + ", status=" + status
                + ", expired=" + expired + "]";
    }
    
}

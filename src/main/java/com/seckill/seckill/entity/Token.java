package com.seckill.seckill.entity;

import java.util.Date;

public class Token {
    
    private int id;
    private String tokenString;
    private int userId;
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
    public String getTokenString() {
        return tokenString;
    }
    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
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
        return "LoginToken [id=" + id + ", user=" + userId + ", token=" + tokenString + ", status=" + status
                + ", expired=" + expired + "]";
    }
    
}

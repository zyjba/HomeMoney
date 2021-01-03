package com.jishou.entity;

import java.util.Date;

public class Income {
    private Integer id;
    private Date time;
    private Double number;
    private String member;
    private String type;
    private String remark;
    private User user;
    public Income() { }
    public Income(Integer id, Date time, Double number, String member, String type, String remark) {
        this.id = id;
        this.time = time;
        this.number = number;
        this.member = member;
        this.type = type;
        this.remark = remark;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", time=" + time +
                ", number=" + number +
                ", member='" + member + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", user=" + user +
                '}';
    }



}

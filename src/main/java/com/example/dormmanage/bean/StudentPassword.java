package com.example.dormmanage.bean;

/**
 * @author 龙
 */
public class StudentPassword {
    private Integer id;

    private String password;

    private Integer stuInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStuInfoId() {
        return stuInfoId;
    }

    public void setStuInfoId(Integer stuInfoId) {
        this.stuInfoId = stuInfoId;
    }
}
package com.example.dormmanage.bean;

import java.util.Date;

public class Outsider {
    private Integer id;

    private String name;

    private String buildingno;

    private Integer dormno;

    private String matter;

    private Date accesstime;

    private Date leavetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBuildingno() {
        return buildingno;
    }

    public void setBuildingno(String buildingno) {
        this.buildingno = buildingno == null ? null : buildingno.trim();
    }

    public Integer getDormno() {
        return dormno;
    }

    public void setDormno(Integer dormno) {
        this.dormno = dormno;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter == null ? null : matter.trim();
    }

    public Date getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(Date accesstime) {
        this.accesstime = accesstime;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }
}
package com.example.dormmanage.bean;

/**
 * @author 龙
 */
public class DormMaintenance {
    private Integer id;

    private Integer dormId;

    private String matter;

    private Integer serviceManId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter == null ? null : matter.trim();
    }

    public Integer getServiceManId() {
        return serviceManId;
    }

    public void setServiceManId(Integer serviceManId) {
        this.serviceManId = serviceManId;
    }
}
package com.example.dormmanage.bean;

/**
 * @author 龙
 */
public class BuildManage {
    private Integer id;

    private String buildingNo;

    private Integer dormManagerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    public Integer getDormManagerId() {
        return dormManagerId;
    }

    public void setDormManagerId(Integer dormManagerId) {
        this.dormManagerId = dormManagerId;
    }
}
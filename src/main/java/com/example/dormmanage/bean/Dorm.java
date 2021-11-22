package com.example.dormmanage.bean;

/**
 * @author 龙
 */
public class Dorm {
    private Integer id;

    private String buildingNo;

    private Integer dormitoryNo;

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

    public Integer getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(Integer dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }
}
package com.example.dormmanage.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 龙
 */
@Data
public class StudentInfo {
    private Integer id;

    private String stuId;

    private String name;

    private String buildingNo;

    private Integer dormitoryNo;

    private String bedNo;

    private String checkInTime;

    private String checkOutTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo == null ? null : bedNo.trim();
    }


}
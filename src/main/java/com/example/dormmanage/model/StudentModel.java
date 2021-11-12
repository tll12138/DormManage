package com.example.dormmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LL
 * @date 2021/11/12 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {

    private Integer id;
    /**
     * 学号
     */
    private Integer stuId;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 宿舍楼
     */
    private String buildingNo;

    /**
     * 宿舍号
     */
    private Integer dormitoryNo;

    /**
     * 床位
     */
    private String bedNo;

    /**
     * 入住时间
     */
    private String check_inTime;

    /**
     * 退宿时间
     */
    private String check_outTime;


}

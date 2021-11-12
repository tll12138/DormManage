package com.example.dormmanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LL
 * @date 2021/11/12 14:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {

    private Integer id;
    /**
     * 学号
     */
    private Integer stuId;

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

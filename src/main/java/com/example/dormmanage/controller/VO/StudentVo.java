package com.example.dormmanage.controller.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LL
 * @date 2021/11/18 19:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private Integer id;

    private String stuId;

    private String name;

    private String buildingNo;

    private Integer dormitoryNo;

    private String bedNo;

    private String checkInTime;

    private String checkOutTime;
}

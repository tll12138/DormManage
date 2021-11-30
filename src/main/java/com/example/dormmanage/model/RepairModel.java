package com.example.dormmanage.model;

import lombok.Data;

/**
 * @author LL
 * @date 2021/11/29 19:07
 */
@Data
public class RepairModel {
    private Integer id;

    private String buildingNo;

    private Integer dormitoryNo;

    private String matter;

    private String state;
}

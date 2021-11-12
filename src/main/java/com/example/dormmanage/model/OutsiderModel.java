package com.example.dormmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LL
 * @date 2021/11/12 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutsiderModel {

    private Integer id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 事情
     */
    private String matter;

    /**
     * 来访时间
     */
    private String accessTime;

    /**
     * 离开时间
     */
    private String leaveTime;

}

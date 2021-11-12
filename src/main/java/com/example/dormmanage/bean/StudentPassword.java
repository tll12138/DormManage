package com.example.dormmanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LL
 * @date 2021/11/12 14:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPassword {

    private Integer id;
    /**
     * 密码
     */
    private String password;
    /**
     * 学生信息表对应id
     */
    private Integer stuInfoId;
}

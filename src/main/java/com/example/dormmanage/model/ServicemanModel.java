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
public class ServicemanModel {

    private Integer id;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;


    public String getUserName() {
        return userName;
    }

}

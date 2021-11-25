package com.example.dormmanage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LL
 * @date 2021/11/12 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormManagerModel {

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

    /**
     * 宿舍楼
     */
    private String buildingNo;

    /**
     * 权限
     */
    private Integer permission;

}

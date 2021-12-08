package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.LogisticsManagerModel;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/23 14:25
 */
public interface LogisticsManagerService {

    /**
     * 根据用户名获取后勤管理员
     * @param username
     * @return
     */
    LogisticsManagerModel getLogisticsManager(String username) throws BusinessException;

    /**
     * 修改密码
     */
    void changePassword(Map<String,Object> map) throws UnsupportedEncodingException, BusinessException;
}

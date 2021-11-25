package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.LogisticsManagerModel;

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
}

package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.DormManagerModel;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/23 13:37
 */
public interface DormManagerService {
    /**
     * 根据username查找寝室管理员
     * @param username
     * @return
     */
    DormManagerModel getDormManagerByUsername(String username) throws BusinessException;

    /**
     * 修改密码
     * @param map
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     */
    void changePassword(Map<String,Object> map) throws BusinessException, UnsupportedEncodingException;
}

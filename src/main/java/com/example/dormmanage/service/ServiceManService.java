package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.ServicemanModel;

/**
 * @author LL
 * @date 2021/11/23 14:39
 */
public interface ServiceManService {
    /**
     * 根据用户名获取修理工
     * @param username
     * @return
     */
    ServicemanModel getServiceMan(String username) throws BusinessException;
}

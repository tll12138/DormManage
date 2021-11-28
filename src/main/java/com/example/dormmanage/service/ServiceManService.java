package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.ServicemanModel;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取所有修理员
     * @param map
     * @return
     */
    List<ServicemanModel> getAll(Map<String,Object> map);

    /**
     * 获取所有维修员数量
     * @return
     */
    Integer getCount();

    /**
     * 新增新的维修员
     * @param map
     */
    void add(Map<String,Object> map) throws BusinessException, UnsupportedEncodingException;

    /**
     * 删除对应的维修员
     * @param map
     */
    void delete(Map<String,Object> map) throws BusinessException;

}

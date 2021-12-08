package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.ManagerModel;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/28 14:21
 */
public interface ManagerService {

    /**
     * 获取宿舍管理员和后勤管理员的集合
     * @param map
     * @return
     */
    List<ManagerModel> getManagers(Map<String,Object> map) throws BusinessException;

    /**
     * 获取所有管理员数
     * @return
     */
    Integer getCount();

    /**
     * 增加管理员
     * @param map
     */
    void addManagers(Map<String,Object> map) throws BusinessException, UnsupportedEncodingException;

    /**
     * 根据传来的数据删除对应管理员
     * @param map
     */
    void deleteManager(Map<String,Object> map) throws BusinessException;

    /**
     * 根据传来的数据登记外来人员
     * @param map
     * @throws BusinessException
     */
    void addOutsider(Map<String,Object> map) throws BusinessException;

}

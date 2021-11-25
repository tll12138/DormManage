package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.DormModel;

import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/25 18:31
 */
public interface DormService {

    /**
     * 获取所有寝室信息
     * @param map
     * @return
     */
    List<DormModel> getDorms(Map<String,Object> map);

    /**
     * 获取所有寝室数量
     * @return
     */
    Integer getCount();

    /**
     * 增加新的寝室,并初始化床位
     * @param map
     */
    void addDorm(Map<String,Object> map) throws BusinessException;

    /**
     * 删除寝室，并删除对应的床位
     * @param map
     */
    void delete(Map<String,Object>map) throws BusinessException;
}

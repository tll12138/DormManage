package com.example.dormmanage.dao;

import com.example.dormmanage.bean.BedsManager;

import java.util.List;
import java.util.Map;

public interface BedsManagerMapper {
    int deleteByPrimaryKey(Integer dormId);

    int insert(BedsManager record);

    int insertSelective(BedsManager record);

    int insertSelectiveByMap(Map<String,Object> map);

    BedsManager selectByPrimaryKey(Integer dormId);

    int updateByPrimaryKeySelective(BedsManager record);

    int updateByMapSelective(Map<String,Object> map);

    int updateByPrimaryKey(BedsManager record);
}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.LogisticsManager;

import java.util.List;
import java.util.Map;

public interface LogisticsManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByMap(Map<String,Object> map);

    int insert(LogisticsManager record);

    int insertSelective(LogisticsManager record);

    int insertSelectiveByMap(Map<String,Object> map);

    LogisticsManager selectByPrimaryKey(Integer id);

    LogisticsManager selectByUsername(String Username);

    List<LogisticsManager> selectByMap(Map<String,Object> map);

    Integer selectCounts();

    int updateByPrimaryKeySelective(LogisticsManager record);

    int updateByPrimaryKey(LogisticsManager record);

}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.LogisticsManager;

public interface LogisticsManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogisticsManager record);

    int insertSelective(LogisticsManager record);

    LogisticsManager selectByPrimaryKey(Integer id);

    LogisticsManager selectByUsername(String Username);

    int updateByPrimaryKeySelective(LogisticsManager record);

    int updateByPrimaryKey(LogisticsManager record);
}
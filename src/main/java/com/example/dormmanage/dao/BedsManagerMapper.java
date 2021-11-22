package com.example.dormmanage.dao;

import com.example.dormmanage.bean.BedsManager;

public interface BedsManagerMapper {
    int deleteByPrimaryKey(Integer dormId);

    int insert(BedsManager record);

    int insertSelective(BedsManager record);

    BedsManager selectByPrimaryKey(Integer dormId);

    int updateByPrimaryKeySelective(BedsManager record);

    int updateByPrimaryKey(BedsManager record);
}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.Outsider;

import java.util.Map;

public interface OutsiderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outsider record);

    int insertSelective(Outsider record);

    int insertSelectiveByMap(Map<String,Object> map);

    Outsider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Outsider record);

    int updateByPrimaryKey(Outsider record);
}
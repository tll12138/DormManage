package com.example.dormmanage.dao;

import com.example.dormmanage.bean.Outsider;

public interface OutsiderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outsider record);

    int insertSelective(Outsider record);

    Outsider selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Outsider record);

    int updateByPrimaryKey(Outsider record);
}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.Dorm;

public interface DormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dorm record);

    int insertSelective(Dorm record);

    Dorm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dorm record);

    int updateByPrimaryKey(Dorm record);
}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.DormManager;

public interface DormManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DormManager record);

    int insertSelective(DormManager record);

    DormManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DormManager record);

    int updateByPrimaryKey(DormManager record);
}
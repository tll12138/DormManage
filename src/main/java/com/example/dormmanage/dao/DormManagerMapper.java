package com.example.dormmanage.dao;

import com.example.dormmanage.bean.DormManager;

public interface DormManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DormManager record);

    int insertSelective(DormManager record);

    DormManager selectByPrimaryKey(Integer id);

    DormManager selectByUsername(String username);

    DormManager selectByName(String name);

    int updateByPrimaryKeySelective(DormManager record);

    int updateByPrimaryKey(DormManager record);
}
package com.example.dormmanage.dao;

import com.example.dormmanage.bean.BuildManage;

public interface BuildManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuildManage record);

    int insertSelective(BuildManage record);

    BuildManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuildManage record);

    int updateByPrimaryKey(BuildManage record);
}
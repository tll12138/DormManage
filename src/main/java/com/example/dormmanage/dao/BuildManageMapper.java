package com.example.dormmanage.dao;

import com.example.dormmanage.bean.BuildManage;

import java.util.List;
import java.util.Map;

public interface BuildManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuildManage record);

    int insertSelective(BuildManage record);

    int insertSelectiveByMap(Map<String,Object> map);

    BuildManage selectByPrimaryKey(Integer id);

    BuildManage selectByBuildNo(String buildNo);

    int selectCount();

    List<BuildManage> selectBuilds(Map<String,Object> map);

    List<BuildManage> selectBuildsSelective(Map<String,Object> map);

    int updateByPrimaryKeySelective(Map<String,Object> map);

    int updateByPrimaryKey(BuildManage record);
}
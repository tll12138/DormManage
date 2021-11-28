package com.example.dormmanage.dao;

import com.example.dormmanage.bean.DormManager;

import java.util.List;
import java.util.Map;

public interface DormManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByMap(Map<String,Object> map);

    int insert(DormManager record);

    int insertSelective(DormManager record);

    int insertSelectiveByMap(Map<String,Object> map);

    DormManager selectByPrimaryKey(Integer id);

    DormManager selectByUsername(String username);

    DormManager selectByName(String name);

    List<DormManager> selectByMap(Map<String,Object> map);

    Integer selectCounts();

    int updateByPrimaryKeySelective(DormManager record);

    int updateByPrimaryKey(DormManager record);
}
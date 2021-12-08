package com.example.dormmanage.dao;

import com.example.dormmanage.bean.Dorm;

import java.util.List;
import java.util.Map;

public interface DormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dorm record);

    int insertSelective(Dorm record);

    int insertSelectiveByMap(Map<String,Object> map);

    Dorm selectByPrimaryKey(Integer id);

    Dorm selectByDormNoAndBuildNo(String buildNo,Integer dormNo);

    Dorm selectByMapSelective(Map<String,Object> map);

    List<Dorm> selectSelective(Map<String,Object> map);

    List<Dorm> selectDorms(Map<String,Object> map);

    int selectCount();

    int updateByPrimaryKeySelective(Dorm record);

    int updateByPrimaryKey(Dorm record);
}
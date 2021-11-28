package com.example.dormmanage.dao;

import com.example.dormmanage.bean.ServiceMan;

import java.util.List;
import java.util.Map;

public interface ServiceManMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMan record);

    int insertSelective(ServiceMan record);

    int insertSelectiveByMap(Map<String,Object> map);

    ServiceMan selectByPrimaryKey(Integer id);

    ServiceMan selectByUsername(String username);

    List<ServiceMan> selectByMap(Map<String,Object> map);

    int selectCount();

    int updateByPrimaryKeySelective(ServiceMan record);

    int updateByPrimaryKey(ServiceMan record);
}
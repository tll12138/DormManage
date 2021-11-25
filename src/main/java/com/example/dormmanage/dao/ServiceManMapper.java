package com.example.dormmanage.dao;

import com.example.dormmanage.bean.ServiceMan;

public interface ServiceManMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMan record);

    int insertSelective(ServiceMan record);

    ServiceMan selectByPrimaryKey(Integer id);

    ServiceMan selectByUsername(String username);

    int updateByPrimaryKeySelective(ServiceMan record);

    int updateByPrimaryKey(ServiceMan record);
}
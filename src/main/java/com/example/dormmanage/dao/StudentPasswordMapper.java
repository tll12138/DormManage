package com.example.dormmanage.dao;

import com.example.dormmanage.bean.StudentPassword;

public interface StudentPasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentPassword record);

    int insertSelective(StudentPassword record);

    StudentPassword selectByPrimaryKey(Integer id);

    StudentPassword selectByStuId(Integer stuInfoId);

    int updateByPrimaryKeySelective(StudentPassword record);

    int updateByPrimaryKey(StudentPassword record);
}
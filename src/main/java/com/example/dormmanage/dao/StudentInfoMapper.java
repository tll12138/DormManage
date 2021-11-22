package com.example.dormmanage.dao;

import com.example.dormmanage.bean.StudentInfo;
import com.example.dormmanage.controller.VO.StudentVo;

import java.util.List;
import java.util.Map;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByStuId(String stuId);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    int insertSelectiveByMap(Map<String,Object> map);

    StudentInfo selectByPrimaryKey(Integer id);

    StudentInfo selectByStuId(String stuId);

    List<StudentInfo> selectStus(Map<String,Object> map);

    List<StudentInfo> selectStudentSelective(Map<String,Object> map);

    Integer selectCounts();

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateSelective(Map<String,Object> map);

    int updateByPrimaryKey(StudentInfo record);
}
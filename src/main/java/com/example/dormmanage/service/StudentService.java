package com.example.dormmanage.service;

import com.example.dormmanage.controller.VO.StudentVo;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.StudentModel;

import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/12 15:44
 */
public interface StudentService {
    /**
     * 根据学生学号获取学生信息
     * @param stuId
     * @return
     */
    StudentModel getStudent(String stuId);

    /**
     * 修改学生信息
     * @param map
     */
    void editStudent(Map<String,Object> map) throws BusinessException;

    /**
     * 添加一个学生信息
     * @param map
     */
    void addStudent(Map<String,Object> map) throws BusinessException;

    /**
     * 获取所有学生信息
     * @return
     */
    List<StudentVo> getUsers(Map<String,Object> map) throws BusinessException;

    /**
     * 查找总记录数
     * @return
     */
    Integer selectCount() throws BusinessException;

    /**
     * 删除学生记录
     * @param map
     */
    void deleteStuByStuId(Map<String,Object> map) throws BusinessException;

    /**
     * 根据搜索条件返回对应条件的学生
     * @param map
     * @return
     */
    List<StudentVo> selectStudent(Map<String,Object> map) throws BusinessException;
}

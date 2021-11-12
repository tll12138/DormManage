package com.example.dormmanage.service;

import com.example.dormmanage.model.StudentModel;

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
     * 根据学生学号修改学生信息
     * @param stuId
     */
    void modifyStudent(String stuId);

    /**
     * 添加一个学生信息
     * @param studentModel
     */
    void addStudent(StudentModel studentModel);
}

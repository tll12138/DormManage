package com.example.dormmanage.service.impl;

import com.example.dormmanage.dao.StudentInfoMapper;
import com.example.dormmanage.dao.StudentPasswordMapper;
import com.example.dormmanage.model.StudentModel;
import com.example.dormmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LL
 * @date 2021/11/12 15:53
 */
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Autowired
    StudentPasswordMapper studentPasswordMapper;

    @Override
    public StudentModel getStudent(String stuId) {
        return null;
    }

    @Override
    public void modifyStudent(String stuId) {

    }

    @Override
    public void addStudent(StudentModel studentModel) {

    }
}

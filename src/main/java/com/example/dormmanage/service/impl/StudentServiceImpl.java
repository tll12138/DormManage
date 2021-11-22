package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.StudentInfo;
import com.example.dormmanage.bean.StudentPassword;
import com.example.dormmanage.controller.VO.StudentVo;
import com.example.dormmanage.dao.StudentInfoMapper;
import com.example.dormmanage.dao.StudentPasswordMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.StudentModel;
import com.example.dormmanage.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/12 15:53
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoMapper studentInfoMapper;

    @Autowired
    StudentPasswordMapper studentPasswordMapper;

    @Override
    public StudentModel getStudent(String stuId) {
        StudentInfo studentInfo = studentInfoMapper.selectByStuId(stuId);
        if (studentInfo==null){
            return null;
        }
        StudentModel studentModel = convertModelFromBean(studentInfo);
        return studentModel;

    }

    @Override
    public void editStudent(Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentInfoMapper.updateSelective(map);
    }

    @Override
    public void addStudent(Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        String stuId = map.get("stuId").toString();
        StudentInfo studentInfo = studentInfoMapper.selectByStuId(stuId);
        if (studentInfo==null){
            studentInfoMapper.insertSelectiveByMap(map);
            StudentPassword studentPassword = new StudentPassword();
            studentPassword.setStuInfoId(studentInfoMapper.selectByStuId(stuId).getId());
            studentPasswordMapper.insertSelective(studentPassword);
        }else {
            throw new BusinessException(EmBusinessError.STUDENT_EXIST);
        }
    }

    @Override
    public List<StudentVo> getUsers(Map<String,Object> map) throws BusinessException {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<StudentInfo> stuInfos = studentInfoMapper.selectStus(map);
        if (stuInfos==null){
            throw new BusinessException(EmBusinessError.STUDENTS_SELECT_NOT_SUCCESS);
        }
        List<StudentVo> studentVos = conventStudentVOFromInfo(stuInfos);
        return studentVos;
    }

    @Override
    public Integer selectCount() {
        Integer counts = studentInfoMapper.selectCounts();
        return counts;
    }

    @Override
    public void deleteStuByStuId(Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        StudentInfo studentInfo = new StudentInfo();
        if (map.containsKey("stuId")){
            String stuId = map.get("stuId").toString();
            studentInfo = studentInfoMapper.selectByStuId(stuId);
        }
        if (studentInfo==null){
            throw new BusinessException(EmBusinessError.STUDENT_NOT_EXIST);
        }
        studentInfoMapper.deleteByStuId(studentInfo.getStuId());
    }

    @Override
    public List<StudentVo> selectStudent(Map<String, Object> map) throws BusinessException {

        if (map==null){
            return null;
        }
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<StudentInfo> studentInfos = studentInfoMapper.selectStudentSelective(map);
        if (studentInfos!=null){
            List<StudentVo> studentVos = conventStudentVOFromInfo(studentInfos);
            return studentVos;
        }
        return null;
    }


    private StudentModel convertModelFromBean(StudentInfo studentInfo){
        if (studentInfo==null){
            return null;
        }
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(studentInfo, studentModel);
        StudentPassword studentPassword = studentPasswordMapper.selectByStuId(studentInfo.getId());
        studentModel.setPassword(studentPassword.getPassword());
        return studentModel;
    }
    private List<StudentVo> conventStudentVOFromInfo(List<StudentInfo> list){
        List<StudentVo> result = new ArrayList<>();
        for(StudentInfo s:list){
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(s, studentVo);
            result.add(studentVo);
        }
        return result;
    }
    private StudentInfo convertStudentInfoFromModel(StudentModel studentModel) throws BusinessException {
        if (studentModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(studentModel, studentInfo);
        return studentInfo;
    }
}

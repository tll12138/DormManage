package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.StudentInfo;
import com.example.dormmanage.bean.StudentPassword;
import com.example.dormmanage.controller.VO.StudentVo;
import com.example.dormmanage.dao.DormMaintenanceMapper;
import com.example.dormmanage.dao.StudentInfoMapper;
import com.example.dormmanage.dao.StudentPasswordMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.BuildModel;
import com.example.dormmanage.model.DormModel;
import com.example.dormmanage.model.StudentModel;
import com.example.dormmanage.service.BulidManageService;
import com.example.dormmanage.service.DormService;
import com.example.dormmanage.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    DormMaintenanceMapper dormMaintenanceMapper;

    @Autowired
    DormService dormService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    BulidManageService bulidManageService;



    @Override
    public StudentModel getStudent(String stuId) throws BusinessException {
        StudentInfo studentInfo = studentInfoMapper.selectByStuId(stuId);
        if (studentInfo==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        StudentModel studentModel = convertModelFromBean(studentInfo);
        return studentModel;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editStudent(Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        String buildingNo = map.get("buildingNo").toString();
        Integer dormitoryNo = Integer.parseInt(map.get("dormitoryNo").toString());
        String bedNo = map.get("bedNo").toString();
        bulidManageService.selectBuild(buildingNo);
        dormService.getDorm(buildingNo, dormitoryNo);
        dormService.getBeds(buildingNo, dormitoryNo, bedNo);
        studentInfoMapper.updateSelective(map);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStudent(Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        String stuId = map.get("stuId").toString();
        String name = map.get("name").toString();
        String buildingNo = map.get("buildingNo").toString();
        Integer dormitoryNo = Integer.parseInt(map.get("dormitoryNo").toString());
        String bedNo = map.get("bedNo").toString();
        bulidManageService.selectBuild(buildingNo);
        dormService.getDorm(buildingNo, dormitoryNo);
        dormService.getBeds(buildingNo, dormitoryNo, bedNo);
        StudentInfo studentInfo = studentInfoMapper.selectByStuId(stuId);
        if (studentInfo==null){
            studentInfoMapper.insertSelectiveByMap(map);
            StudentPassword studentPassword = new StudentPassword();
            studentPassword.setStuInfoId(studentInfoMapper.selectByStuId(stuId).getId());
            studentPasswordMapper.insertSelective(studentPassword);
            dormService.addStu(name,buildingNo,dormitoryNo,bedNo);
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
    @Transactional(rollbackFor = Exception.class)
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
        String buildingNo = map.get("buildingNo").toString();
        Integer dormitoryNo = Integer.parseInt(map.get("dormitoryNo").toString());
        String bedNo = map.get("bedNo").toString();
        bulidManageService.selectBuild(buildingNo);
        dormService.getDorm(buildingNo, dormitoryNo);
        dormService.deleteStu(buildingNo, dormitoryNo, bedNo);
        studentInfoMapper.deleteByStuId(studentInfo.getStuId());
        studentPasswordMapper.deleteByStuInfoId(studentInfo.getId());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void repair(Map<String, Object> map) throws BusinessException {
        if (map.containsKey("stuId")){
            String stuId = (String) map.get("stuId");
            StudentInfo studentInfo = studentInfoMapper.selectByStuId(stuId);
            if (studentInfo==null){
                throw new BusinessException(EmBusinessError.STUDENT_NOT_EXIST);
            }
        }
        map.put("size", 1);
        map.put("page", 1);
        List<DormModel> dormModels = dormService.selectByMap(map);
        if (dormModels.size()>1){
            throw new BusinessException(EmBusinessError.DATABASE_NOT_SUCCESS);
        }else if (dormModels.size()==0){
            throw new BusinessException(EmBusinessError.DORM_NOT_EXIST);
        }
        DormModel dormModel = dormModels.get(0);
        map.put("dormId", dormModel.getId());
        dormMaintenanceMapper.insertSelectiveByMap(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(Map<String, Object> map) throws BusinessException, UnsupportedEncodingException {
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        StudentInfo studentInfo = studentInfoMapper.selectByStuId(username);
        StudentPassword studentPassword = studentPasswordMapper.selectByStuId(studentInfo.getId());
        if (map.containsKey("old_password")){
            String oldPassword = (String) map.get("old_password");
            if (!StringUtils.equals(studentPassword.getPassword(), new String(Base64.encodeBase64(oldPassword.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PASSWORD_NOT_TRUE);
            }
            if (map.containsKey("new_password")){
                String newPassword = (String) map.get("new_password");
                studentPassword.setPassword(new String(Base64.encodeBase64(newPassword.getBytes("UTF-8"))));
                studentPasswordMapper.updateByPrimaryKeySelective(studentPassword);
            }
        }
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

package com.example.dormmanage.controller;

import com.example.dormmanage.controller.VO.StudentVo;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.DormManagerModel;
import com.example.dormmanage.model.LogisticsManagerModel;
import com.example.dormmanage.model.ServicemanModel;
import com.example.dormmanage.model.StudentModel;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.*;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LL
 * @date 2021/11/16 19:47
 */
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController extends BaseController{


    @Autowired
    StudentService studentService;

    @Autowired
    DormManagerService dormManagerService;

    @Autowired
    BulidManageService bulidManageService;

    @Autowired
    DormService dormService;

    @Autowired
    LogisticsManagerService logisticsManagerService;

    @Autowired
    ServiceManService serviceManService;

    @Autowired
    HttpServletRequest httpServletRequest;

    //学生学号位数
    public final static Integer STUDENT_NUMBERS = 10;

    //寝室管理员用户名位数
    public final static Integer DORMMANGER_NUMBERS = 6;

    //后勤管理员用户名位数
    public final static Integer LOGISTICSMANAGER_NUMBERS = 5;

    //维修工用户名位数
    public final static Integer SERVICEMAN_NUMBERS = 4;

    Integer permission = -1;

    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturn login(@RequestParam("username")String username,
                              @RequestParam("password")String password) throws Exception {
        if (StringUtil.isNullOrEmpty(username)){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE, "账号不能为空");
        }
        if (StringUtil.isNullOrEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE, "密码不能为空");
        }

        if (username.length()==STUDENT_NUMBERS){
            StudentModel student = studentService.getStudent(username);
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(student,studentVo);
            httpServletRequest.getSession().setAttribute("student", studentVo);
            if (!StringUtils.equals(student.getPassword(), new String(Base64.encodeBase64(password.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE,"密码错误");
            }
        }else if (username.length()==DORMMANGER_NUMBERS){
            DormManagerModel dormManagerModel = dormManagerService.getDormManagerByUsername(username);
            httpServletRequest.getSession().setAttribute("dormManager", dormManagerModel);
            if (!StringUtils.equals(dormManagerModel.getPassword(), new String(Base64.encodeBase64(password.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE,"密码错误");
            }
            permission = dormManagerModel.getPermission();
        }else if (username.length()==LOGISTICSMANAGER_NUMBERS){
            LogisticsManagerModel logisticsManager = logisticsManagerService.getLogisticsManager(username);
            httpServletRequest.getSession().setAttribute("logisticsManager",logisticsManager);
            if (!StringUtils.equals(logisticsManager.getPassword(), new String(Base64.encodeBase64(password.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE,"密码错误");
            }
            permission = logisticsManager.getPermission();
        }else if (username.length()==SERVICEMAN_NUMBERS){
            ServicemanModel serviceMan = serviceManService.getServiceMan(username);
            httpServletRequest.getSession().setAttribute("serviceMan",serviceMan);
            if (!StringUtils.equals(serviceMan.getPassword(), new String(Base64.encodeBase64(password.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE,"密码错误");
            }
            permission = serviceMan.getPermission();
        }else {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        httpServletRequest.getSession().setAttribute("username", username);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        return CommonReturn.create(null);
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public CommonReturn initUsers(@RequestParam Map<String,Object> map) throws Exception {

        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }

        List<StudentVo> users = studentService.getUsers(map);
        if (users==null){
            throw new BusinessException(EmBusinessError.DATABASE_NOT_SUCCESS);
        }
        Integer count = studentService.selectCount();
        if (count==null){
            throw new BusinessException(EmBusinessError.DATABASE_NOT_SUCCESS);
        }
        return CommonReturn.create(users,count,"200");
    }

    @RequestMapping("/add")
    @ResponseBody
    public CommonReturn add(@RequestBody Map<String,Object> map) throws BusinessException {

        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.addStudent(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturn delete(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.deleteStuByStuId(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public CommonReturn edit(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.editStudent(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn select(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<StudentVo> studentVos = studentService.selectStudent(map);
        return CommonReturn.create(studentVos,1,"200");
    }

    @RequestMapping("/index")
    @ResponseBody
    public CommonReturn index() throws BusinessException {
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        Map<String, Object> map = new HashMap<>(8);
        map.put("username",username);
        map.put("permission",permission);
        return CommonReturn.create(map);
    }

    @RequestMapping("/repair")
    @ResponseBody
    public CommonReturn repair(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.repair(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public CommonReturn changePassword(@RequestBody Map<String,Object> map) throws BusinessException, UnsupportedEncodingException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        if (username.length()==STUDENT_NUMBERS){
            studentService.changePassword(map);
        }else if (username.length()==LOGISTICSMANAGER_NUMBERS){
            logisticsManagerService.changePassword(map);
        }else if (username.length()==DORMMANGER_NUMBERS){
            dormManagerService.changePassword(map);
        }else if (username.length()==SERVICEMAN_NUMBERS){
            serviceManService.changePassword(map);
        }
        return CommonReturn.create(null);
    }
}

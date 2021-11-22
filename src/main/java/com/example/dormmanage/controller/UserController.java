package com.example.dormmanage.controller;

import com.example.dormmanage.bean.StudentInfo;
import com.example.dormmanage.controller.VO.StudentVo;
import com.example.dormmanage.dao.StudentInfoMapper;
import com.example.dormmanage.dao.StudentPasswordMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.StudentModel;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.StudentService;
import com.sun.deploy.net.HttpResponse;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.MarshalledObject;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    StudentInfoMapper studentInfoMapper;

    @Autowired
    StudentPasswordMapper studentPasswordMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturn login(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                              Model model) throws Exception {
        if (StringUtil.isNullOrEmpty(username)){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE, "账号不能为空");
        }
        if (StringUtil.isNullOrEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE, "密码不能为空");
        }
        StudentModel student = studentService.getStudent(username);
        if (student==null){
            throw new BusinessException(EmBusinessError.STUDENT_NOT_EXIST);
        }
        if (!StringUtils.equals(student.getPassword(), new String(Base64.encodeBase64(password.getBytes("UTF-8"))))){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE,"密码错误");
        }
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(student,studentVo);
        model.addAttribute("username", username);
        model.addAttribute("student", studentVo);
        httpServletRequest.getSession().setAttribute("username", username);
        httpServletRequest.getSession().setAttribute("student", studentVo);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        return CommonReturn.create(null);
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public CommonReturn initUsers(@RequestParam Map<String,Object> map,
                                  HttpSession session) throws Exception {

        if (map==null){
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

        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.addStudent(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturn delete(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.deleteStuByStuId(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public CommonReturn edit(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        studentService.editStudent(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn select(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<StudentVo> studentVos = studentService.selectStudent(map);
        return CommonReturn.create(studentVos,1,"200");
    }
}

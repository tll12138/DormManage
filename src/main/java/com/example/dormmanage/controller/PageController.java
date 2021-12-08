package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.BulidManageService;
import com.example.dormmanage.service.DormManagerService;
import com.example.dormmanage.service.DormService;
import com.example.dormmanage.service.StudentService;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/29 14:38
 */
@Controller
@RequestMapping("page")
public class PageController {

    @Autowired
    StudentService studentService;

    @Autowired
    DormManagerService dormManagerService;

    @Autowired
    BulidManageService bulidManageService;

    @Autowired
    DormService dormService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/welcome")
    @ResponseBody
    public CommonReturn welcome() throws BusinessException {
        Integer studentCount = studentService.selectCount();
        Integer buildCount = bulidManageService.selectCount();
        Integer dormCount = dormService.getCount();
        Map<String, Object> map = new HashMap<>(4);
        map.put("studentCount", studentCount);
        map.put("buildCount",buildCount );
        map.put("dormCount", dormCount);
        return CommonReturn.create(map);
    }
    @RequestMapping("/username")
    @ResponseBody
    public CommonReturn getUsername() throws BusinessException {
        String username =String.valueOf(httpServletRequest.getSession().getAttribute("username"));
        Map<String, Object> map = new HashMap<>(4);
        map.put("username", username);
        return CommonReturn.create(map);
    }


}

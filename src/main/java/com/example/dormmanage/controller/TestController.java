package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LL
 * @date 2021/11/19 19:46
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/test1")
    @ResponseBody
    public CommonReturn test() throws BusinessException {
        Integer count = studentService.selectCount();
        return CommonReturn.create(count);
    }
}

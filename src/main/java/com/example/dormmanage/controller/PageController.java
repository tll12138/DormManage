package com.example.dormmanage.controller;

import com.example.dormmanage.service.StudentService;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    HttpServletRequest httpServletRequest;

    @RequestMapping("/getUsername")
    public ModelAndView getUsername(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", httpServletRequest.getSession().getAttribute("username"));
        return modelAndView;
    }

}

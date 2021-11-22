package com.example.dormmanage.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author LL
 * @date 2021/11/22 17:24
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("IS_LOGIN");
        if (loginUser!=null) {
            //放行
            return true;
        }
        String requestURI = request.getRequestURI();
        log.info("拦截的请求{}",requestURI);
        //拦截,未登录，跳转到登录页
        request.setAttribute("msg", "请先登录");
//        request.getRequestDispatcher("/").forward(request, response);
        response.sendRedirect("http://localhost:8888/login.html");
        return false;
    }
}

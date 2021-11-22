package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.response.CommonReturn;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author LL
 * @date 2021/11/16 15:07
 */
@Controller
public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(Exception exception){
        HashMap<String, Object> map = new HashMap<>(16);
        if (exception instanceof BusinessException){
            BusinessException businessException = (BusinessException) exception;
            map.put("errCode", businessException.getErrCode());
            map.put("errMsg", businessException.getErrMsg());
        }else {
            map.put("errCode", EmBusinessError.PARAMETER_NOT_LEGITIMATE.getErrCode());
            map.put("errMsg", EmBusinessError.PARAMETER_NOT_LEGITIMATE.getErrMsg());
        }
        return CommonReturn.create(map,"fail");
    }
}

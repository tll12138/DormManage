package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.ManagerModel;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/28 14:15
 */
@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController{

    @Autowired
    ManagerService managerService;

    @RequestMapping("/managers")
    @ResponseBody
    public CommonReturn getManagers(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        List<ManagerModel> managers = managerService.getManagers(map);
        return CommonReturn.create(managers, managerService.getCount());
    }

    @RequestMapping("/add")
    @ResponseBody
    public CommonReturn addManager(@RequestBody Map<String,Object> map) throws Exception {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        managerService.addManagers(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public CommonReturn deleteManager(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        managerService.deleteManager(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn selectManagers(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        List<ManagerModel> managers = managerService.getManagers(map);
        return CommonReturn.create(managers,managers.size());
    }
}

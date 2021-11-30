package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.RepairModel;
import com.example.dormmanage.model.ServicemanModel;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.ServiceManService;
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
 * @date 2021/11/28 16:02
 */
@Slf4j
@Controller
@RequestMapping("/serviceman")
public class ServiceManController {

    @Autowired
    ServiceManService service;

    @RequestMapping("/all")
    @ResponseBody
    public CommonReturn getAll(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        List<ServicemanModel> servicemanModels = service.getAll(map);
        return CommonReturn.create(servicemanModels, service.getCount());
    }
    @RequestMapping("/add")
    @ResponseBody
    public CommonReturn addServiceMan(@RequestBody Map<String,Object> map) throws Exception {
        if (map == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        service.add(map);
        return CommonReturn.create(null);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturn deleteServiceMan(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        service.delete(map);
        return CommonReturn.create(null);
    }
    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn selectServiceMan(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        List<ServicemanModel> servicemanModels = service.getAll(map);
        return CommonReturn.create(servicemanModels, servicemanModels.size());
    }
    @RequestMapping("/repairs")
    @ResponseBody
    public CommonReturn getRepairs(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null){
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        List<RepairModel> repairs = service.getRepairs(map);
        return CommonReturn.create(repairs,service.getRepairsCount());
    }
    @RequestMapping("/finish")
    @ResponseBody
    public CommonReturn stateFinish(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_NOT_LEGITIMATE);
        }
        service.finishRepair(map);
        return CommonReturn.create(null);
    }
}

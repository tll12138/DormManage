package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.BuildModel;
import com.example.dormmanage.response.CommonReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.dormmanage.service.BulidManageService;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/24 13:57
 */
@Slf4j
@Controller
@RequestMapping("/build")
public class BuildController extends BaseController{

    @Autowired
    BulidManageService bulidManageService;

    @RequestMapping("/builds")
    @ResponseBody
    public CommonReturn initBuilds(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<BuildModel> builds = bulidManageService.getBuilds(map);
        Integer count = bulidManageService.selectCount();
        return CommonReturn.create(builds,count);
    }

    @RequestMapping("/add")
    @ResponseBody
    public CommonReturn add(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        bulidManageService.addBuild(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturn delete(@RequestBody Map<String,Object> map) throws BusinessException {
        if (!map.containsKey("id")){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        bulidManageService.deleteBuild(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public CommonReturn edit(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        bulidManageService.editBuild(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn select(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<BuildModel> buildModels = bulidManageService.selectBuilds(map);
        return CommonReturn.create(buildModels,buildModels.size());
    }

}

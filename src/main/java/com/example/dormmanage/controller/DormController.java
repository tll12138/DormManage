package com.example.dormmanage.controller;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.BedsModel;
import com.example.dormmanage.model.DormModel;
import com.example.dormmanage.response.CommonReturn;
import com.example.dormmanage.service.DormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/25 18:31
 */
@Slf4j
@Controller
@RequestMapping("/dorm")
public class DormController extends BaseController{

    @Autowired
    DormService dormService;

    @RequestMapping("/dorms")
    @ResponseBody
    public CommonReturn getDorms(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<DormModel> dorms = dormService.getDorms(map);
        return CommonReturn.create(dorms,dormService.getCount());
    }

    @RequestMapping("/add")
    @ResponseBody
    public CommonReturn add(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        dormService.addDorm(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturn delete(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        dormService.delete(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public CommonReturn edit(@RequestBody Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        dormService.editBedsForDorm(map);
        return CommonReturn.create(null);
    }

    @RequestMapping("/select")
    @ResponseBody
    public CommonReturn select(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        List<DormModel> dormModels = dormService.selectByMap(map);
        return CommonReturn.create(dormModels, dormModels.size());
    }

    @RequestMapping("/beds")
    @ResponseBody
    public CommonReturn getBeds(@RequestParam Map<String,Object> map) throws BusinessException {
        if (map==null||map.size()==0){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        BedsModel beds = dormService.getBeds(map);
        return CommonReturn.create(beds,1);
    }
}

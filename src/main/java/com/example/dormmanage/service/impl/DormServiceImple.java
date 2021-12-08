package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.BedsManager;
import com.example.dormmanage.dao.BedsManagerMapper;
import com.example.dormmanage.bean.Dorm;
import com.example.dormmanage.dao.DormMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.BedsModel;
import com.example.dormmanage.model.DormModel;
import com.example.dormmanage.service.DormService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/25 18:34
 */
@Service
public class DormServiceImple implements DormService {

    @Autowired
    DormMapper dormMapper;

    @Autowired
    BedsManagerMapper bedsManagerMapper;

    @Override
    public List<DormModel> getDorms(Map<String, Object> map) {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<Dorm> dorms = dormMapper.selectDorms(map);
        if (dorms!=null){
            List<DormModel> dormModels = convertModelFromBean(dorms);
            return dormModels;
        }
        return null;
    }

    @Override
    public Integer getCount() {
        return dormMapper.selectCount();
    }

    @Override
    public void addDorm(Map<String, Object> map) throws BusinessException {
        Dorm dorm = dormMapper.selectByMapSelective(map);
        if (dorm!=null){
            throw new BusinessException(EmBusinessError.DORM_IS_EXIST);
        }
        dormMapper.insertSelectiveByMap(map);
        Dorm newDorm = dormMapper.selectByMapSelective(map);
        BedsManager bedsManager = new BedsManager();
        bedsManager.setBeda("");
        bedsManager.setBedb("");
        bedsManager.setBedc("");
        bedsManager.setBedd("");
        bedsManager.setDormId(newDorm.getId());
        bedsManagerMapper.insertSelective(bedsManager);

    }

    @Override
    public void delete(Map<String, Object> map) throws BusinessException {
        if (!map.containsKey("id")){
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        Integer id = (Integer) map.get("id");
        dormMapper.deleteByPrimaryKey(id);
        bedsManagerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editBedsForDorm(Map<String, Object> map) throws BusinessException {
        Dorm dorm = dormMapper.selectByMapSelective(map);
        if (dorm==null){
            throw new BusinessException(EmBusinessError.DORM_NOT_EXIST);
        }
        map.put("dormId", dorm.getId());
        bedsManagerMapper.updateByMapSelective(map);
    }

    @Override
    public List<DormModel> selectByMap(Map<String, Object> map) throws BusinessException {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<Dorm> dorms = dormMapper.selectSelective(map);
        if (dorms==null){
            throw new BusinessException(EmBusinessError.DORM_NOT_EXIST);
        }
        List<DormModel> dormModels = convertModelFromBean(dorms);
        return dormModels;
    }

    @Override
    public BedsModel getBeds(Map<String, Object> map) throws BusinessException {
        Integer dormitoryNo = Integer.parseInt((String) map.get("dormitoryNo"));
        map.put("dormitoryNo",dormitoryNo);
        Dorm dorm = dormMapper.selectByMapSelective(map);
        BedsManager bedsManager = bedsManagerMapper.selectByPrimaryKey(dorm.getId());
        BedsModel bedsModel = convertModelFromBean(bedsManager);
        return bedsModel;
    }

    @Override
    public void getDorm(String buildNo,Integer dormitoryNo) throws BusinessException {
        Dorm dorm = dormMapper.selectByDormNoAndBuildNo(buildNo,dormitoryNo);
        if (dorm==null){
            throw new BusinessException(EmBusinessError.DORM_NOT_EXIST);
        }
    }

    @Override
    public void getBeds(String buildNo, Integer dormitoryNo, String bedNo) throws BusinessException {
        Dorm dorm = dormMapper.selectByDormNoAndBuildNo(buildNo,dormitoryNo);
        BedsManager bedsManager = bedsManagerMapper.selectByPrimaryKey(dorm.getId());
        if ("A".equals(bedNo)){
            if (!StringUtil.isNullOrEmpty(bedsManager.getBeda())) {
                throw new BusinessException(EmBusinessError.BED_IS_HAVE);
            }
        }else if ("B".equals(bedNo)){
            if (!StringUtil.isNullOrEmpty(bedsManager.getBedb())) {
                throw new BusinessException(EmBusinessError.BED_IS_HAVE);
            }
        }else if ("C".equals(bedNo)){
            if (!StringUtil.isNullOrEmpty(bedsManager.getBedc())) {
                throw new BusinessException(EmBusinessError.BED_IS_HAVE);
            }
        }else if ("D".equals(bedNo)){
            if (!StringUtil.isNullOrEmpty(bedsManager.getBedd())) {
                throw new BusinessException(EmBusinessError.BED_IS_HAVE);
            }
        }
    }

    @Override
    public void addStu(String name, String buildNo, Integer dormitoryNo, String bedNo) throws BusinessException {
        Dorm dorm = dormMapper.selectByDormNoAndBuildNo(buildNo,dormitoryNo);
        BedsManager bedsManager = bedsManagerMapper.selectByPrimaryKey(dorm.getId());
        if ("A".equals(bedNo)){
            bedsManager.setBeda(name);
        }else if ("B".equals(bedNo)){
            bedsManager.setBedb(name);
        }else if ("C".equals(bedNo)){
            bedsManager.setBedc(name);
        }else if ("D".equals(bedNo)){
            bedsManager.setBedd(name);
        }
        bedsManagerMapper.updateByPrimaryKey(bedsManager);
    }

    @Override
    public void deleteStu(String buildNo, Integer dormitoryNo, String bedNo) throws BusinessException {
        Dorm dorm = dormMapper.selectByDormNoAndBuildNo(buildNo,dormitoryNo);
        BedsManager bedsManager = bedsManagerMapper.selectByPrimaryKey(dorm.getId());
        if ("A".equals(bedNo)){
            if (StringUtils.isNotEmpty(bedsManager.getBeda())) {
                bedsManager.setBeda("");
            }else {
                throw new BusinessException(EmBusinessError.BED_NOT_HAVE);
            }
        }else if ("B".equals(bedNo)){
            if (StringUtils.isNotEmpty(bedsManager.getBedb())) {
                bedsManager.setBedb("");
            }else {
                throw new BusinessException(EmBusinessError.BED_NOT_HAVE);
            }
        }else if ("C".equals(bedNo)){
            if (StringUtils.isNotEmpty(bedsManager.getBedc())) {
                bedsManager.setBedc("");
            }else {
                throw new BusinessException(EmBusinessError.BED_NOT_HAVE);
            }
        }else if ("D".equals(bedNo)){
            if (StringUtils.isNotEmpty(bedsManager.getBedd())) {
                bedsManager.setBedd("");
            }else {
                throw new BusinessException(EmBusinessError.BED_NOT_HAVE);
            }
        }
        bedsManagerMapper.updateByPrimaryKey(bedsManager);
    }

    private List<DormModel> convertModelFromBean(List<Dorm> list){
        List<DormModel> dormModels = new ArrayList<>();
        for (Dorm dorm:list){
            DormModel dormModel = new DormModel();
            BeanUtils.copyProperties(dorm, dormModel);
            dormModels.add(dormModel);
        }
        return dormModels;
    }

    private BedsModel convertModelFromBean(BedsManager bedsManager){
        BedsModel bedsModel = new BedsModel();
        BeanUtils.copyProperties(bedsManager, bedsModel);
        bedsModel.setDormNo(bedsManager.getDormId());
        return bedsModel;
    }
}

package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.BedsManager;
import com.example.dormmanage.dao.BedsManagerMapper;
import com.example.dormmanage.bean.Dorm;
import com.example.dormmanage.dao.DormMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.DormModel;
import com.example.dormmanage.service.DormService;
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
        Dorm dorm = dormMapper.selectByMapPrimaryKey(map);
        if (dorm!=null){
            throw new BusinessException(EmBusinessError.DORM_IS_EXIST);
        }
        dormMapper.insertSelectiveByMap(map);
        Dorm newDorm = dormMapper.selectByMapPrimaryKey(map);
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

    private List<DormModel> convertModelFromBean(List<Dorm> list){
        List<DormModel> dormModels = new ArrayList<>();
        for (Dorm dorm:list){
            DormModel dormModel = new DormModel();
            BeanUtils.copyProperties(dorm, dormModel);
            dormModels.add(dormModel);
        }
        return dormModels;
    }
}

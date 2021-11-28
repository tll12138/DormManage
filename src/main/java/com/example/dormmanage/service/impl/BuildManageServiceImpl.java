package com.example.dormmanage.service.impl;
import com.example.dormmanage.bean.BuildManage;
import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.BuildModel;
import com.example.dormmanage.service.BulidManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dormmanage.dao.BuildManageMapper;
import com.example.dormmanage.dao.DormManagerMapper;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/24 14:05
 */
@Service
public class BuildManageServiceImpl implements BulidManageService {

    @Autowired
    BuildManageMapper buildManageMapper;

    @Autowired
    DormManagerMapper dormManagerMapper;

    @Override
    public List<BuildModel> getBuilds(Map<String, Object> map) {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<BuildManage> buildManages = buildManageMapper.selectBuilds(map);
        if (buildManages==null){
            return null;
        }
        List<BuildModel> buildModels = convertModelFromBean(buildManages);

        return buildModels;
    }

    @Override
    public Integer selectCount() throws BusinessException {

        int count = buildManageMapper.selectCount();
        if (count<0){
            throw new BusinessException(EmBusinessError.DATABASE_NOT_SUCCESS);
        }
        return count;
    }

    @Override
    public void addBuild(Map<String, Object> map) throws BusinessException {
        String name = (String) map.get("name");
        DormManager dormManager = dormManagerMapper.selectByName(name);
        if (dormManager==null){
            throw new BusinessException(EmBusinessError.DORM_MANAGER_NOT_EXIST);
        }
        map.put("dormManagerId", dormManager.getId());
        String buildingNo = (String) map.get("buildingNo");
        BuildManage buildManage = buildManageMapper.selectByBuildNo(buildingNo);
        if (buildManage==null){
            buildManageMapper.insertSelectiveByMap(map);
        }else {
            throw new BusinessException(EmBusinessError.BUILDING_IS_EXIST);
        }
    }

    @Override
    public void deleteBuild(Map<String, Object> map) {
        Integer id = (Integer) map.get("id");
        buildManageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editBuild(Map<String, Object> map) throws BusinessException {
        String name = (String) map.get("name");
        DormManager dormManager = dormManagerMapper.selectByName(name);
        map.put("dormManagerId", dormManager.getId());
        Integer id = Integer.parseInt((String) map.get("id"));
        if (id==null){
            throw new BusinessException(EmBusinessError.BUILD_ID_NOT_EXIST);
        }
        BuildManage buildManage = buildManageMapper.selectByPrimaryKey(id);
        if (buildManage==null){
            throw new BusinessException(EmBusinessError.BUILD_ID_NOT_EXIST);


        }
        buildManageMapper.updateByPrimaryKeySelective(map);
    }

    @Override
    public List<BuildModel> selectBuilds(Map<String, Object> map) {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        if (map.containsKey("name")) {
            String name = (String) map.get("name");
            DormManager dormManager = dormManagerMapper.selectByName(name);
            map.put("dormManagerId", dormManager.getId());
        }
        List<BuildManage> buildManages = buildManageMapper.selectBuildsSelective(map);
        if (buildManages!=null){
            List<BuildModel> buildModels = convertModelFromBean(buildManages);
            return buildModels;
        }
        return null;
    }

    private List<BuildModel> convertModelFromBean(List<BuildManage> list){
        List<BuildModel> buildModels = new ArrayList<>();
        for (BuildManage buildManage:list){
            BuildModel buildModel = new BuildModel();
            BeanUtils.copyProperties(buildManage, buildModel);
            DormManager dormManager = dormManagerMapper.selectByPrimaryKey(buildManage.getDormManagerId());
            buildModel.setName(dormManager.getName());
            buildModels.add(buildModel);
        }
        return buildModels;
    }

}

package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.bean.LogisticsManager;
import com.example.dormmanage.dao.DormManagerMapper;
import com.example.dormmanage.dao.LogisticsManagerMapper;
import com.example.dormmanage.dao.OutsiderMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.ManagerModel;
import com.example.dormmanage.service.ManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/28 14:22
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    DormManagerMapper dormManagerMapper;

    @Autowired
    LogisticsManagerMapper logisticsManagerMapper;

    @Autowired
    OutsiderMapper outsiderMapper;

    private static final String DORM_MANAGER = "寝室管理员";
    private static final String LOGISTICS_MANAGER = "后勤管理员";

    @Override
    public List<ManagerModel> getManagers(Map<String, Object> map) throws BusinessException {
        Integer page = 1,size=5;
        String identity;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        if (map.containsKey("identity")){
            identity = (String) map.get("identity");
            if (DORM_MANAGER.equals(identity)){
                List<DormManager> dormManagers = dormManagerMapper.selectByMap(map);
                List<ManagerModel> managerModels = convertModelFromDormManager(dormManagers);
                return managerModels;
            }else if (LOGISTICS_MANAGER.equals(identity)){
                List<LogisticsManager> logisticsManagers = logisticsManagerMapper.selectByMap(map);
                List<ManagerModel> managerModels = convertModelFromLogisticsManager(logisticsManagers);
                return managerModels;
            }else {
                throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
            }
        }

        List<DormManager> dormManagers = dormManagerMapper.selectByMap(map);
        List<LogisticsManager> logisticsManagers = logisticsManagerMapper.selectByMap(map);

        List<ManagerModel> managerModels = convertModelFromDormManager(dormManagers);
        List<ManagerModel> managerModels1 = convertModelFromLogisticsManager(logisticsManagers);
        managerModels.addAll(managerModels1);

        return managerModels;
    }

    @Override
    public Integer getCount() {
        return dormManagerMapper.selectCounts()+ logisticsManagerMapper.selectCounts();
    }

    @Override
    public void addManagers(Map<String, Object> map) throws BusinessException, UnsupportedEncodingException {
        String identity;
        if (map.containsKey("identity")){
            identity = (String) map.get("identity");
        }else {
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        if (DORM_MANAGER.equals(identity)){
            String username = (String) map.get("username");
            DormManager dormManager = dormManagerMapper.selectByUsername(username);
            if (dormManager!=null){
                throw new BusinessException(EmBusinessError.DORM_MANAGER_IS_EXIST);
            }
            String password = (String) map.get("password");
            password = new String(Base64.encodeBase64(password.getBytes("UTF-8")));
            map.put("password",password);
            dormManagerMapper.insertSelectiveByMap(map);
            return;
        }else if (LOGISTICS_MANAGER.equals(identity)){
            String username = (String) map.get("username");
            LogisticsManager logisticsManager = logisticsManagerMapper.selectByUsername(username);
            if (logisticsManager!=null){
                throw new BusinessException(EmBusinessError.LOGISTICS_MANAGER_IS_EXIST);
            }
            String password = (String) map.get("password");
            password = new String(Base64.encodeBase64(password.getBytes("UTF-8")));
            map.put("password",password);
            logisticsManagerMapper.insertSelectiveByMap(map);
            return;
        }else {
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
    }

    @Override
    public void deleteManager(Map<String, Object> map) throws BusinessException {
        String identity;
        if (map.containsKey("identity")){
            identity = (String) map.get("identity");
        }else {
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
        if (DORM_MANAGER.equals(identity)){
            String username = (String) map.get("username");
            DormManager dormManager = dormManagerMapper.selectByUsername(username);
            if (dormManager==null){
                throw new BusinessException(EmBusinessError.DORM_MANAGER_NOT_EXIST);
            }
            dormManagerMapper.deleteByMap(map);
            return;
        }else if (LOGISTICS_MANAGER.equals(identity)){
            String username = (String) map.get("username");
            LogisticsManager logisticsManager = logisticsManagerMapper.selectByUsername(username);
            if (logisticsManager==null){
                throw new BusinessException(EmBusinessError.LOGISTICS_MANAGER_NOT_EXIST);
            }
            logisticsManagerMapper.deleteByMap(map);
            return;
        }else {
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
    }

    @Override
    public void addOutsider(Map<String, Object> map) throws BusinessException {
        outsiderMapper.insertSelectiveByMap(map);
    }

    private List<ManagerModel> convertModelFromDormManager(List<DormManager> list){
        List<ManagerModel> managerModels = new ArrayList<>();
        for (DormManager dormManager:list){
            ManagerModel managerModel = new ManagerModel();
            BeanUtils.copyProperties(dormManager, managerModel);
            managerModel.setIdentity("寝室管理员");
            managerModels.add(managerModel);
        }
        return managerModels;
    }
    private List<ManagerModel> convertModelFromLogisticsManager(List<LogisticsManager> list){
        List<ManagerModel> managerModels = new ArrayList<>();
        for (LogisticsManager logisticsManager:list){
            ManagerModel managerModel = new ManagerModel();
            BeanUtils.copyProperties(logisticsManager, managerModel);
            managerModel.setIdentity("后勤管理员");
            managerModels.add(managerModel);
        }
        return managerModels;
    }
}

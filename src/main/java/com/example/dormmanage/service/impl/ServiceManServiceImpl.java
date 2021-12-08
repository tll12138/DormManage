package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.Dorm;
import com.example.dormmanage.bean.DormMaintenance;
import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.bean.ServiceMan;
import com.example.dormmanage.dao.DormMaintenanceMapper;
import com.example.dormmanage.dao.DormMapper;
import com.example.dormmanage.dao.ServiceManMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.RepairModel;
import com.example.dormmanage.model.ServicemanModel;
import com.example.dormmanage.service.ServiceManService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/23 14:40
 */
@Service
public class ServiceManServiceImpl implements ServiceManService {

    @Autowired
    ServiceManMapper serviceManMapper;

    @Autowired
    DormMaintenanceMapper dormMaintenanceMapper;

    @Autowired
    DormMapper dormMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public ServicemanModel getServiceMan(String username) throws BusinessException {
        if (StringUtil.isNullOrEmpty(username)){
            return null;
        }
        ServiceMan serviceMan = serviceManMapper.selectByUsername(username);
        if (serviceMan==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        ServicemanModel servicemanModel = convertModelFromBean(serviceMan);
        return servicemanModel;
    }

    @Override
    public List<ServicemanModel> getAll(Map<String, Object> map) {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<ServiceMan> serviceMEN = serviceManMapper.selectByMap(map);
        List<ServicemanModel> servicemanModels = convertModelFromServiceMan(serviceMEN);
        return servicemanModels;
    }

    @Override
    public Integer getCount() {
        return serviceManMapper.selectCount();
    }

    @Override
    public void add(Map<String, Object> map) throws BusinessException, UnsupportedEncodingException {
        String username = (String) map.get("username");
        ServiceMan serviceMan = serviceManMapper.selectByUsername(username);
        if (serviceMan!=null){
            throw new BusinessException(EmBusinessError.SERVICE_MAN_IS_EXIST);
        }
        String password = (String) map.get("password");
        password = new String(Base64.encodeBase64(password.getBytes("UTF-8")));
        map.put("password",password);
        serviceManMapper.insertSelectiveByMap(map);
    }

    @Override
    public void delete(Map<String, Object> map) throws BusinessException {
        Integer id = (Integer) map.get("id");
        ServiceMan serviceMan = serviceManMapper.selectByPrimaryKey(id);
        if (serviceMan==null){
            throw new BusinessException(EmBusinessError.SERVICE_MAN_NOT_EXIST);
        }
        serviceManMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RepairModel> getRepairs(Map<String, Object> map) throws BusinessException {
        Integer page = 1,size=5;
        if (map.containsKey("limit")){
            size=Integer.parseInt(map.get("limit").toString());
            map.put("size", size);
        }
        if (map.containsKey("page")){
            page= Integer.parseInt(map.get("page").toString());
            map.put("page", (page-1)*size);
        }
        List<DormMaintenance> dormMaintenances = dormMaintenanceMapper.selectRepairs(map);
        List<RepairModel> repairModels = convertModelFromDormMaintenance(dormMaintenances);
        return repairModels;
    }

    @Override
    public Integer getRepairsCount() {
        return dormMaintenanceMapper.selectCount();
    }

    @Override
    public void finishRepair(Map<String, Object> map) throws BusinessException {
        if (map.containsKey("id")){
            Integer id = (Integer) map.get("id");
            DormMaintenance dormMaintenance = dormMaintenanceMapper.selectByPrimaryKey(id);
            if (dormMaintenance.getState().equals("完成")){
                throw new BusinessException(EmBusinessError.REPAIR_ALREADY_FINISH);
            }
            dormMaintenanceMapper.updateByIdSelective(id);
        }else {
            throw new BusinessException(EmBusinessError.FRONT_PARAMETER_NOT_LEGITIMATE);
        }
    }

    @Override
    public void changePassword(Map<String, Object> map) throws BusinessException, UnsupportedEncodingException {
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        ServiceMan serviceMan = serviceManMapper.selectByUsername(username);
        if (map.containsKey("old_password")){
            String oldPassword = (String) map.get("old_password");
            if (!StringUtils.equals(serviceMan.getPassword(), new String(Base64.encodeBase64(oldPassword.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PASSWORD_NOT_TRUE);
            }
            if (map.containsKey("new_password")){
                String newPassword = (String) map.get("new_password");
                serviceMan.setPassword(new String(Base64.encodeBase64(newPassword.getBytes("UTF-8"))));
                serviceManMapper.updateByPrimaryKeySelective(serviceMan);
            }
        }
    }

    public ServicemanModel convertModelFromBean(ServiceMan serviceMan){
        ServicemanModel servicemanModel = new ServicemanModel();
        BeanUtils.copyProperties(serviceMan, servicemanModel);
        return servicemanModel;
    }
    private List<ServicemanModel> convertModelFromServiceMan(List<ServiceMan> list){
        List<ServicemanModel> servicemanModels = new ArrayList<>();
        for (ServiceMan serviceMan:list){
            ServicemanModel servicemanModel = new ServicemanModel();
            BeanUtils.copyProperties(serviceMan, servicemanModel);
            servicemanModels.add(servicemanModel);
        }
        return servicemanModels;
    }
    private List<RepairModel> convertModelFromDormMaintenance(List<DormMaintenance> list){
        List<RepairModel> repairModels = new ArrayList<>();
        for(DormMaintenance dormMaintenance:list){
            RepairModel repairModel = new RepairModel();
            Dorm dorm = dormMapper.selectByPrimaryKey(dormMaintenance.getDormId());
            BeanUtils.copyProperties(dormMaintenance, repairModel);
            repairModel.setBuildingNo(dorm.getBuildingNo());
            repairModel.setDormitoryNo(dorm.getDormitoryNo());
            repairModels.add(repairModel);
        }
        return repairModels;
    }
}

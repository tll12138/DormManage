package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.bean.ServiceMan;
import com.example.dormmanage.dao.ServiceManMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.ManagerModel;
import com.example.dormmanage.model.ServicemanModel;
import com.example.dormmanage.service.ServiceManService;
import io.netty.util.internal.StringUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.stereotype.Service;

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
}

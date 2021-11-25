package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.ServiceMan;
import com.example.dormmanage.dao.ServiceManMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.ServicemanModel;
import com.example.dormmanage.service.ServiceManService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.stereotype.Service;

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
    public ServicemanModel convertModelFromBean(ServiceMan serviceMan){
        ServicemanModel servicemanModel = new ServicemanModel();
        BeanUtils.copyProperties(serviceMan, servicemanModel);
        return servicemanModel;
    }
}

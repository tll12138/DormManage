package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.LogisticsManager;
import com.example.dormmanage.dao.LogisticsManagerMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.LogisticsManagerModel;
import com.example.dormmanage.service.LogisticsManagerService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/23 14:26
 */
@Service
public class LogisticsManagerServiceImpl implements LogisticsManagerService {

    @Autowired
    LogisticsManagerMapper logisticsManagerMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public LogisticsManagerModel getLogisticsManager(String username) throws BusinessException {

        if (StringUtil.isNullOrEmpty(username)){
            return null;
        }
        LogisticsManager logisticsManager = logisticsManagerMapper.selectByUsername(username);
        if (logisticsManager==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        LogisticsManagerModel logisticsManagerModel = convertModelFromBean(logisticsManager);
        return logisticsManagerModel;
    }

    @Override
    public void changePassword(Map<String, Object> map) throws UnsupportedEncodingException, BusinessException {
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        LogisticsManager logisticsManager = logisticsManagerMapper.selectByUsername(username);
        if (map.containsKey("old_password")){
            String oldPassword = (String) map.get("old_password");
            if (!StringUtils.equals(logisticsManager.getPassword(), new String(Base64.encodeBase64(oldPassword.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PASSWORD_NOT_TRUE);
            }
            if (map.containsKey("new_password")){
                String newPassword = (String) map.get("new_password");
                logisticsManager.setPassword(new String(Base64.encodeBase64(newPassword.getBytes("UTF-8"))));
                logisticsManagerMapper.updateByPrimaryKeySelective(logisticsManager);
            }
        }
    }


    public LogisticsManagerModel convertModelFromBean(LogisticsManager logisticsManager){
        LogisticsManagerModel logisticsManagerModel = new LogisticsManagerModel();
        BeanUtils.copyProperties(logisticsManager, logisticsManagerModel);
        return logisticsManagerModel;
    }
}

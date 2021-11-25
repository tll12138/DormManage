package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.LogisticsManager;
import com.example.dormmanage.dao.LogisticsManagerMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.LogisticsManagerModel;
import com.example.dormmanage.service.LogisticsManagerService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LL
 * @date 2021/11/23 14:26
 */
@Service
public class LogisticsManagerServiceImpl implements LogisticsManagerService {

    @Autowired
    LogisticsManagerMapper logisticsManagerMapper;

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
    public LogisticsManagerModel convertModelFromBean(LogisticsManager logisticsManager){
        LogisticsManagerModel logisticsManagerModel = new LogisticsManagerModel();
        BeanUtils.copyProperties(logisticsManager, logisticsManagerModel);
        return logisticsManagerModel;
    }
}

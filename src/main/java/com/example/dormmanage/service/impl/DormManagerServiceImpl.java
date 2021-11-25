package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.dao.DormManagerMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.DormManagerModel;
import com.example.dormmanage.service.DormManagerService;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LL
 * @date 2021/11/23 14:02
 */
@Service
public class DormManagerServiceImpl implements DormManagerService {

    @Autowired
    DormManagerMapper dormManagerMapper;

    @Override
    public DormManagerModel getDormManagerByUsername(String username) throws BusinessException {
        if (StringUtil.isNullOrEmpty(username)){
            return null;
        }
        DormManager dormManager = dormManagerMapper.selectByUsername(username);
        if (dormManager==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        DormManagerModel dormManagerModel = convertModelFromBean(dormManager);
        return dormManagerModel;
    }

    public DormManagerModel convertModelFromBean(DormManager dormManager){
        DormManagerModel dormManagerModel = new DormManagerModel();
        BeanUtils.copyProperties(dormManager, dormManagerModel);
        return dormManagerModel;
    }
}

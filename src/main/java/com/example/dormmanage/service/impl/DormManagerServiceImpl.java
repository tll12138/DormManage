package com.example.dormmanage.service.impl;

import com.example.dormmanage.bean.DormManager;
import com.example.dormmanage.bean.StudentInfo;
import com.example.dormmanage.bean.StudentPassword;
import com.example.dormmanage.dao.DormManagerMapper;
import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.error.EmBusinessError;
import com.example.dormmanage.model.DormManagerModel;
import com.example.dormmanage.service.DormManagerService;
import io.netty.util.NettyRuntime;
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
 * @date 2021/11/23 14:02
 */
@Service
public class DormManagerServiceImpl implements DormManagerService {

    @Autowired
    DormManagerMapper dormManagerMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

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

    @Override
    public void changePassword(Map<String, Object> map) throws BusinessException, UnsupportedEncodingException {
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        DormManager dormManager = dormManagerMapper.selectByUsername(username);
        if (map.containsKey("old_password")){
            String oldPassword = (String) map.get("old_password");
            if (!StringUtils.equals(dormManager.getPassword(), new String(Base64.encodeBase64(oldPassword.getBytes("UTF-8"))))){
                throw new BusinessException(EmBusinessError.PASSWORD_NOT_TRUE);
            }
            if (map.containsKey("new_password")){
                String newPassword = (String) map.get("new_password");
                dormManager.setPassword(new String(Base64.encodeBase64(newPassword.getBytes("UTF-8"))));
                dormManagerMapper.updateByPrimaryKeySelective(dormManager);
            }
        }
    }

    public DormManagerModel convertModelFromBean(DormManager dormManager){
        DormManagerModel dormManagerModel = new DormManagerModel();
        BeanUtils.copyProperties(dormManager, dormManagerModel);
        return dormManagerModel;
    }
}

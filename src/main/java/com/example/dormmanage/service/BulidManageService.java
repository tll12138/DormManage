package com.example.dormmanage.service;

import com.example.dormmanage.error.BusinessException;
import com.example.dormmanage.model.BuildModel;

import java.util.List;
import java.util.Map;

/**
 * @author LL
 * @date 2021/11/24 14:02
 */
public interface BulidManageService {

    /**
     * 返回所有楼栋信息
     * @param map
     * @return
     */
    List<BuildModel> getBuilds(Map<String,Object> map);

    /**
     * 得到共有多少楼栋信息
     * @return
     */
    Integer selectCount() throws BusinessException;

    /**
     * 根据传来的数据创建新的楼栋信息
     * @param map
     */
    void addBuild(Map<String,Object> map) throws BusinessException;

    /**
     * 根据传来的数据删除楼栋信息
     * @param map
     */
    void deleteBuild(Map<String,Object> map);

    /**
     * 根据传来的参数修改楼栋信息
     * @param map
     */
    void editBuild(Map<String,Object> map) throws BusinessException;

    /**
     * 根据条件返回对于的楼栋
     * @param map
     * @return
     */
    List<BuildModel> selectBuilds(Map<String,Object> map);
    /**
     * 按buildingNo返回楼栋
     */
    void selectBuild(String buildingNo) throws BusinessException;
}

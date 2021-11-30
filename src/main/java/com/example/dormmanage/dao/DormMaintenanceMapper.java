package com.example.dormmanage.dao;

import com.example.dormmanage.bean.Dorm;
import com.example.dormmanage.bean.DormMaintenance;

import java.util.List;
import java.util.Map;

public interface DormMaintenanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DormMaintenance record);

    int insertSelective(DormMaintenance record);

    int insertSelectiveByMap(Map<String,Object> map);

    DormMaintenance selectByPrimaryKey(Integer id);

    List<DormMaintenance> selectRepairs(Map<String,Object> map);

    int selectCount();

    int updateByPrimaryKeySelective(DormMaintenance record);

    int updateByIdSelective(Integer id);

    int updateByPrimaryKey(DormMaintenance record);

}
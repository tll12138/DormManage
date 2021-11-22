package com.example.dormmanage.dao;

import com.example.dormmanage.bean.DormMaintenance;

public interface DormMaintenanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DormMaintenance record);

    int insertSelective(DormMaintenance record);

    DormMaintenance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DormMaintenance record);

    int updateByPrimaryKey(DormMaintenance record);
}
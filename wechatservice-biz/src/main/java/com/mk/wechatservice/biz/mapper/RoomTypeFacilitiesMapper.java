package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.RoomTypeFacilities;
import com.mk.wechatservice.biz.model.RoomTypeFacilitiesExample;

import java.util.List;

public interface RoomTypeFacilitiesMapper {
    int countByExample(RoomTypeFacilitiesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypeFacilities record);

    int insertSelective(RoomTypeFacilities record);

    List<RoomTypeFacilities> selectByExample(RoomTypeFacilitiesExample example);

    RoomTypeFacilities selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomTypeFacilities record);

    int updateByPrimaryKey(RoomTypeFacilities record);
}
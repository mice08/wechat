package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.HotelServices;
import com.mk.wechatservice.biz.model.HotelServicesExample;

import java.util.List;

public interface HotelServicesMapper {
    int countByExample(HotelServicesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotelServices record);

    int insertSelective(HotelServices record);

    List<HotelServices> selectByExample(HotelServicesExample example);

    HotelServices selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotelServices record);

    int updateByPrimaryKey(HotelServices record);
}
package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.HotelSurround;
import com.mk.wechatservice.biz.model.HotelSurroundExample;
import java.util.List;

public interface HotelSurroundMapper {
    int countByExample(HotelSurroundExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HotelSurround record);

    int insertSelective(HotelSurround record);

    List<HotelSurround> selectByExample(HotelSurroundExample example);

    HotelSurround selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotelSurround record);

    int updateByPrimaryKey(HotelSurround record);
}
package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.Hotel;
import com.mk.wechatservice.biz.model.HotelExample;
import java.util.List;

public interface HotelMapper {
    int countByExample(HotelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    List<Hotel> selectByExample(HotelExample example);

    Hotel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);
}
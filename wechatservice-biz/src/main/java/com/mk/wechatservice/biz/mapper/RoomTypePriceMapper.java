package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.RoomTypePrice;
import com.mk.wechatservice.biz.model.RoomTypePriceExample;
import java.util.List;

public interface RoomTypePriceMapper {
    int countByExample(RoomTypePriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomTypePrice record);

    int insertSelective(RoomTypePrice record);

    List<RoomTypePrice> selectByExample(RoomTypePriceExample example);

    RoomTypePrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomTypePrice record);

    int updateByPrimaryKey(RoomTypePrice record);
}
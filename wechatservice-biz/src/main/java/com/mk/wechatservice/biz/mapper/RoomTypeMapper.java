package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.RoomType;
import com.mk.wechatservice.biz.model.RoomTypeExample;
import java.util.List;

public interface RoomTypeMapper {
    int countByExample(RoomTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    List<RoomType> selectByExample(RoomTypeExample example);

    RoomType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);
}
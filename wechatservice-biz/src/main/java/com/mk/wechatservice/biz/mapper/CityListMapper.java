package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.CityList;
import com.mk.wechatservice.biz.model.CityListExample;

import java.util.List;

public interface CityListMapper {
    int countByExample(CityListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CityList record);

    int insertSelective(CityList record);

    List<CityList> selectByExample(CityListExample example);

    CityList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CityList record);

    int updateByPrimaryKey(CityList record);
}
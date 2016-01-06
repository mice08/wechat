package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.CommentSum;
import com.mk.wechatservice.biz.model.CommentSumExample;

import java.util.List;

public interface CommentSumMapper {
    int countByExample(CommentSumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentSum record);

    int insertSelective(CommentSum record);

    List<CommentSum> selectByExample(CommentSumExample example);

    CommentSum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentSum record);

    int updateByPrimaryKey(CommentSum record);
}
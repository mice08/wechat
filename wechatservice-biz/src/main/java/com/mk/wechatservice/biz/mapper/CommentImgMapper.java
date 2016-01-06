package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.CommentImg;
import com.mk.wechatservice.biz.model.CommentImgExample;

import java.util.List;

public interface CommentImgMapper {
    int countByExample(CommentImgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentImg record);

    int insertSelective(CommentImg record);

    List<CommentImg> selectByExample(CommentImgExample example);

    CommentImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentImg record);

    int updateByPrimaryKey(CommentImg record);
}
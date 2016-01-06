package com.mk.wechatservice.biz.mapper;

import com.mk.wechatservice.biz.model.Comment;
import com.mk.wechatservice.biz.model.CommentExample;

import java.util.List;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
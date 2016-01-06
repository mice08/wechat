package com.mk.wechatservice.biz.mapper.umember;

import java.util.List;

import com.mk.wechatservice.biz.module.UMember;

/**
 * Created by kirinli on 15/9/9.
 */
public interface UMemberMapper {
    List<UMember> getByParams(UMember uMember);
}

package com.mk.wechatservice.biz.mapper.blacklist;

import com.mk.wechatservice.biz.module.PassSwitch;

/**
 * Created by kirinli on 15/9/10.
 */
public interface PassSwitchMapper {
    PassSwitch isClose();
    int updateSwitch(int is_close);

}

package com.mk.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jeashi
 */

public enum CallMethodEnum {
    CRS("1", "crs"),
    WEB("2", "web"),
    WECHAT("3", "wechat"),
    IOS("4", "ios"),
    ANDROID("5", "android"),
    WEIXIN("6", "weixin"),
    UNKNOW("99", "unknow");

    private final String id;
    private final String name;

    private CallMethodEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id;
    }

    public static CallMethodEnum getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return UNKNOW;
        }
        for (CallMethodEnum temp : CallMethodEnum.values()) {
            if (temp.getName().equals(name.toLowerCase())) {
                return temp;
            }
        }
        return UNKNOW;
    }
}

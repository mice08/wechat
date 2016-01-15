package com.mk.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jeashi
 */

public enum PayTypeEnum {
    WECHAT("1", "weichat"),
    ZHIFUBAO("2", "zhifubao"),
    WANGYING("3", "wangying"),
    OTHER("4", "other"),;

    private final String id;
    private final String name;

    private PayTypeEnum(String id, String name) {
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

    public static PayTypeEnum getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return OTHER;
        }
        for (PayTypeEnum temp : PayTypeEnum.values()) {
            if (temp.getName().equals(name.toLowerCase())) {
                return temp;
            }
        }
        return OTHER;
    }
}

package com.mk.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jeashi
 */

public enum OrderTypenum {
    YF("1", " 预付"),
    DF("2", "到付"),
    UK("99", "未知");
    private final String id;
    private final String name;

    private OrderTypenum(String id, String name) {
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

    public static OrderTypenum getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return UK;
        }
        for (OrderTypenum temp : OrderTypenum.values()) {
            if (temp.getName().equals(name.toLowerCase())) {
                return temp;
            }
        }
        return UK;
    }
}

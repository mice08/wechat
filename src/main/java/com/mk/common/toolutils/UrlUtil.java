package com.mk.common.toolutils;

import com.jfinal.kit.Prop;

public class UrlUtil {
    public static Prop prop = new Prop("common_urls.properties", "UTF-8");

    public static String getValue(String key) {
        return prop.get(key);
    }

}

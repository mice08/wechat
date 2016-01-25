package com.mk.common.toolutils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;


public class DataHander {
    public static String checkStringNull(JSONObject jsonObject, String key, String resultDefault) {
        String result = "";
        if (StringUtils.isNotEmpty(resultDefault)) {
            result = resultDefault;
        }
        if (null != jsonObject) {
            if (jsonObject.containsKey(key)) {
                if(StringUtils.isEmpty(jsonObject.getString(key))){
                    return  result;
                }
                return jsonObject.getString(key);
            }
        }

        return result;
    }

    public static String checkStringNull(JSONObject jsonObject, String jsonArrayKey, String key, String resultDefault) {
        String result = "";

        if (StringUtils.isNotEmpty(resultDefault)) {
            result = resultDefault;
        }

        if (null != jsonObject && StringUtils.isNotEmpty(jsonArrayKey) && StringUtils.isNotEmpty(key)) {
            JSONArray jsonArray = jsonObject.getJSONArray(jsonArrayKey);
            if (null != jsonArray&&jsonArray.size()>0) {
                JSONObject jso = jsonArray.getJSONObject(0);
                if (null != jso && jso.containsKey(key)) {
                    if(StringUtils.isEmpty(jso.getString(key))){
                        return  result;
                    }
                    return jso.getString(key);
                }
            }

        }

        return result;
    }

    public static String checkStringNull(JSONObject jsonObject, String jsonArrayKey, String jsonObject2, String key, String resultDefault) {
        String result = "";
        if (StringUtils.isNotEmpty(resultDefault)) {
            result = resultDefault;
        }

        if (null != jsonObject && StringUtils.isNotEmpty(jsonArrayKey) && StringUtils.isNotEmpty(jsonObject2) && StringUtils.isNotEmpty(key)) {
            JSONArray jsonArray = jsonObject.getJSONArray(jsonArrayKey);
            if (null != jsonArray&&jsonArray.size()>0) {
                JSONObject jso = jsonArray.getJSONObject(0);
                if (null != jso) {
                    JSONArray jsa = jso.getJSONArray(jsonObject2);
                    if (null != jsa&&jsa.size()>0) {
                        JSONObject json = jsa.getJSONObject(0);
                        if (null != json && json.containsKey(key)) {
                            String  value = json.getString(key);
                            if(StringUtils.isEmpty(value)){
                                return  result;
                            }
                            return value;
                        }
                    }

                    return jso.getString(key);
                }
            }

        }

        return result;
    }

    public static String getMinMonty(String moneyA, String moneyB) {
        String reuslt = null;
        return reuslt;
    }

    public static void main(String[] args) throws ParseException {
        DataHander dh = new DataHander();
    }

}

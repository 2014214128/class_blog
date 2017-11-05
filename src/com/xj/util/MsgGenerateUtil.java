package com.xj.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxr on 17/4/1.
 */
public class MsgGenerateUtil {
    public static Map<String, Object> getMap() {
        return new HashMap<>();
    }

    public static Map<String, Object> getSuccessMap(String msg) {
        Map<String, Object> map = getMap();
        map.put("success", true);
        map.put("message", msg);
        return map;
    }

    public static Map<String, Object> getSuccessMap() {
        return getSuccessMap("");
    }

    public static Map<String, Object> getErrorMap(String msg) {
        Map<String, Object> map = getMap();
        map.put("success", false);
        map.put("message", msg);
        return map;
    }

    public static Map<String, Object> getErrorMap() {
        return getErrorMap("");
    }


    public static String getSuccessJsonString() {
        return new JSONObject(getSuccessMap()).toString();
    }

    public static String getSuccessJsonString(String msg) {
        return new JSONObject(getSuccessMap(msg)).toString();
    }

    public static String getErrorJsonString() {
        return new JSONObject(getErrorMap()).toString();
    }

    public static String getErrorJsonString(String msg) {
        return new JSONObject(getErrorMap(msg)).toString();
    }
}

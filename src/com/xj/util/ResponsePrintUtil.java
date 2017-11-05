package com.xj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by cxr on 2017/4/9.
 */
public class ResponsePrintUtil {
    public static void print(HttpServletResponse response, String printStr) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = null;
            out = response.getWriter();
            out.println(printStr);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void print(HttpServletResponse response, JSONArray jsonArray) {
        print(response, jsonArray.toString());
    }

    public static void print(HttpServletResponse response, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject(map);
        print(response, jsonObject.toString());
    }


}

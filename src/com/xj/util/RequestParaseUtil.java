package com.xj.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by cxr on 2017/4/9.
 */
public class RequestParaseUtil {
    public static Object parse(HttpServletRequest request, Class t) {
        Method[] methods = t.getMethods();
        Object object = null;
        try {
            object = t.newInstance();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    String methodType = method.getParameterTypes()[0].getSimpleName();
                    String value = request.getParameter(name);
                    if (setVal(object, method, methodType, value)) continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private static boolean setVal(Object object, Method method, String methodType, String value) throws IllegalAccessException, InvocationTargetException, ParseException {
        if (value == null || "".equals(value)) {
            return true;
        }
        if ("int".equals(methodType)) {
            method.invoke(object, Integer.parseInt(value));
        } else if ("long".equals(methodType)) {
            method.invoke(object, Long.parseLong(value));
        } else if ("float".equals(methodType)) {
            method.invoke(object, Float.parseFloat(value));
        } else if ("Date".equals(methodType)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            method.invoke(object, simpleDateFormat.parse(value));
        } else {
            method.invoke(object, value);
        }
        return false;
    }


    public static Object parseFromItem(Object obj, String name, String value) {
        Method method = ResultTransformer.getSetter(name, obj.getClass());
        if (method == null)
            return obj;
        String methodType = method.getParameterTypes()[0].getSimpleName();
        try {
            setVal(obj, method, methodType, value);
        } catch (IllegalAccessException | ParseException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

}

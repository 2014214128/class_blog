package com.xj.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by sheeran on 2017/5/8.
 * Talk is cheap show me code.
 */
public class ResultTransformer {

    public static Object transform(Class clazz, ResultSet rs) {
        Object o = null;
        try {
            o = clazz.newInstance();
//            Field[] fs = clazz.getDeclaredFields();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1, count = metaData.getColumnCount(); i <= count; i++) {
                String columnName = metaData.getColumnName(i);
                Field field = clazz.getDeclaredField(columnName);
                Method m = getSetter(field.getName(), clazz);
                if (m != null) setValue(field, o, m, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }


    private static void setValue(Field f, Object bean, Method m, ResultSet rs) {
        String fieldName = f.getName();
        String type = f.getType().getSimpleName();
        Object v = null;
        try {

            if (type.equals("int") || type.equals("Integer")) {
                v = rs.getInt(fieldName);
            } else if (type.equals("String")) {
                v = rs.getString(fieldName);
            } else if (type.equals("long") || type.equals("Long")) {
                v = rs.getLong(fieldName);
            } else if (type.equals("Date")) {
                v = rs.getDate(fieldName);
            } else if (type.equals("double") || type.equals("Double")) {
                v = rs.getDouble(fieldName);
            } else if (type.equals("short") || type.equals("Short")) {
                v = rs.getShort(fieldName);
            }
            if (v == null) return;
            m.invoke(bean, new Object[]{v});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Method getSetter(String name, Class clazz) {
        String first = name.substring(0, 1);
        name = "set" + first.toUpperCase() + name.substring(1, name.length());
        return findMethod(name, clazz);
    }

    private static Method findMethod(String name, Class clazz) {
        try {
            for (Method method : clazz.getDeclaredMethods()) {
                if (name.equals(method.getName())) {
                    return method;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("没有找到对应的方法");
        }
        return null;
    }


}

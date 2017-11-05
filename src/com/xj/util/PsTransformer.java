package com.xj.util;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/6/2.
 * Talk is cheap show me code.
 */
public class PsTransformer {
    //对Sql语句进行自动的添加parameters操作
    public static void setSqlParamters(String sql, Object obj, PreparedStatement ps) throws Exception {
        Class clazz = obj.getClass();
        List<String> names = getParamNames(sql);
        int index = 1;
        for (String name : names) {
            Field field = null;
            field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            Object val = field.get(obj);
            ps.setObject(index++, val);
        }
    }

    //从sql语句中解析需要注入的参数名字
    private static List<String> getParamNames(String sql) {
        List<String> res = new ArrayList<>();
        String copy = sql.replaceAll("`", "").replaceAll(" ", "");   //formate the string
        int start = copy.indexOf("(");
        int end = copy.indexOf(")", start + 1);
        int comma = start;
        while (comma < end) {
            int aux = copy.indexOf(",", comma + 1);
            String name = null;
            if (aux > end || aux == -1) {
                name = copy.substring(comma + 1, end);
                res.add(name);
                break;
            } else
                name = copy.substring(comma + 1, aux);
            comma = aux;
            res.add(name);
        }
        return res;
    }

}

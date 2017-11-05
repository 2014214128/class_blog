package com.xj.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public class StringManager {
    private final ResourceBundle bundle;
    private static final Map<String, StringManager> managers = new HashMap<>();

    private StringManager(String packageName) {
        bundle = ResourceBundle.getBundle(packageName + ".localStrings");
    }

    public static synchronized StringManager getInstance(String packageName) {
        if (!managers.containsKey(packageName)) {
            StringManager manager = new StringManager(packageName);
            managers.put(packageName, manager);
            return manager;
        }
        return managers.get(packageName);
    }

    public String getString(String keyword) {


        return bundle.getString(keyword);
    }
}

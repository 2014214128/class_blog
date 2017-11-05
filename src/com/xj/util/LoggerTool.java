package com.xj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sheeran on 2017/6/14.
 * Talk is cheap show me code.
 */
public class LoggerTool {

    private static Map<String, LoggerTool> loggers = new HashMap<>();
    private static Logger logger;
    private static final String thisClassName = LoggerTool.class.getName();

    private LoggerTool(Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static LoggerTool newInstance(Class clazz) {
        if (loggers.get(clazz.getName()) == null) {
            if (loggers.get(clazz.getName()) == null) {
                LoggerTool loggerTool = new LoggerTool(clazz);
                loggers.put(clazz.getName(), loggerTool);
            }
        }
        return loggers.get(clazz.getName());
    }

    public void info(String mes) {
        mes = getStackMsg(Thread.currentThread().getStackTrace()) +"\n"+ mes;
        logger.info(mes);
    }

    public void debug(String mes) {
        mes = getStackMsg(Thread.currentThread().getStackTrace())+"\n" + mes;

        logger.debug(mes);
    }

    public void warn(String mes) {
        mes = getStackMsg(Thread.currentThread().getStackTrace()) +"\n"+ mes;

        logger.warn(mes);
    }

    public void error(String mes) {
        mes = getStackMsg(Thread.currentThread().getStackTrace()) +"\n"+ mes;
        logger.error(mes);
    }

    private String getStackMsg(StackTraceElement[] ste) {
        if (ste == null)
            return null;
        boolean srcFlag = false;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement s = ste[i];
            // 如果上一行堆栈代码是本类的堆栈，则该行代码则为源代码的最原始堆栈。
            if (srcFlag) {
                return s == null ? "" : s.toString();
            } // 定位本类的堆栈
            if (thisClassName.equals(s.getClassName())) {
                srcFlag = true;
            }
        }
        return null;
    }
}

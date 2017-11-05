package com.xj.listener;

import com.xj.service.impl.ArticleServiceImpl;
import org.junit.Test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by sheeran on 2017/6/6.
 * Talk is cheap show me code.
 */
@WebListener()
public class ServerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String date=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("  服务器启动"+date);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //关闭服务器的时候，关闭相关的资源
//        ArticleServiceImpl.getService().shutdown();
        Enumeration<Driver> drivers=DriverManager.getDrivers();
        while(drivers.hasMoreElements()){
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String date=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("  服务器关闭"+date);
    }

}

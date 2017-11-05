package com.xj.servlet;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sheeran on 2017/6/14.
 * Talk is cheap show me code.
 */
@WebServlet(name = "loggerInitServlet",urlPatterns = "/loggerInit",initParams = @WebInitParam(name = "logger", value = "WEB-INF\\classes\\log4j.properties"), loadOnStartup = 1)
public class LoggerInitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        String prefix = config.getServletContext().getRealPath("/");
        String file = config.getInitParameter("logger");
        String filePath = prefix + file;
        Properties properties = new Properties();
        try {
            FileInputStream is = new FileInputStream(filePath);
            properties.load(is);
            is.close();
            String logDfile = prefix + properties.getProperty("log4j.appender.D.File");
            String logEfile = prefix + properties.getProperty("log4j.appender.E.File");
            properties.setProperty("log4j.appender.D.File", logDfile);
            properties.setProperty("log4j.appender.E.File", logEfile);
            PropertyConfigurator.configure(properties);
        } catch (IOException e) {
            System.out.println("无法加载log4j的配置文件");
            e.printStackTrace();
        }

    }
}

package com.xj.servlet;

import com.xj.service.SystemDDLService;
import com.xj.service.impl.SystemDDLServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/6/3.
 */
@WebServlet(name = "InitServlet", urlPatterns = "/init", loadOnStartup = 0)
public class InitServlet extends HttpServlet {

    private SystemDDLService systemDDLService = new SystemDDLServiceImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        
        //保存关键字
        List<String> keywords = new ArrayList<>();
        for (Object o : systemDDLService.findKeyword()) {
            keywords.add(o.toString());
        }
        this.getServletContext().setAttribute("keywords", keywords);
        //保存文章类型
        this.getServletContext().setAttribute("categoryList", systemDDLService.findElecSystemDDLByKeyWord("文章类型"));
        //保存文章类别
        this.getServletContext().setAttribute("second_categoryList", systemDDLService.findElecSystemDDLByKeyWord("文章类别"));
        //保存角色类型
        this.getServletContext().setAttribute("gradeList", systemDDLService.findElecSystemDDLByKeyWord("角色类型"));
        //保存普通用户权限
        this.getServletContext().setAttribute("userList", systemDDLService.findElecSystemDDLByKeyWord("普通用户"));
        //保存一般管理员权限
        this.getServletContext().setAttribute("managerList", systemDDLService.findElecSystemDDLByKeyWord("一般管理员"));
        //保存高级管理员权限
        this.getServletContext().setAttribute("superManagerList", systemDDLService.findElecSystemDDLByKeyWord("高级管理员"));
        //保存文章板块
        this.getServletContext().setAttribute("partList", systemDDLService.findElecSystemDDLByKeyWord("文章板块"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

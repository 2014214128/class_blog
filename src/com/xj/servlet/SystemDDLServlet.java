package com.xj.servlet;

import com.xj.domain.SystemDDL;
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
 * Created by zhengguo on 2017/6/2.
 */
@WebServlet(name = "SystemDDLServlet",urlPatterns = "/systemddl")
public class SystemDDLServlet extends HttpServlet {
    private SystemDDLService systemDDLService = new SystemDDLServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        if("new".equals(type)) {
            String keywordname = request.getParameter("keywordname");
            String[] itemname = request.getParameterValues("itemname");
            List<SystemDDL> systemDDLS = systemDDLService.setSystemDDLList(keywordname, itemname);
            systemDDLService.save(systemDDLS);
            List<String> keywords = new ArrayList<>();
            for(Object o : systemDDLService.findKeyword()) {
                keywords.add(o.toString());
            }
            //保存关键字
            this.getServletContext().setAttribute("keywords", keywords);
            response.sendRedirect("/blog/jsps/background/homepage/sortManage.jsp");
        } else if("edit".equals(type)) {
            String keyword = request.getParameter("keyword");
            request.getSession().setAttribute("keyword",keyword);
            List<SystemDDL> list = systemDDLService.findElecSystemDDLByKeyWord(keyword);
            request.getSession().setAttribute("systemList", list);
            response.sendRedirect("/blog/jsps/background/homepage/sortManageEdit.jsp");
        } else if("update".equals(type)) {
            String keyword = request.getParameter("keyword");
            String[] itemname = request.getParameterValues("itemname");
            systemDDLService.updateElecSystemDDL(keyword,itemname);
            List<SystemDDL> list = systemDDLService.findElecSystemDDLByKeyWord(keyword);
            if("文章类型".equals(keyword)) {
                this.getServletContext().setAttribute("categoryList",list);
            } else if("文章类别".equals(keyword)) {
                this.getServletContext().setAttribute("second_categoryList",list);
            } else if("角色类型".equals(keyword)) {
                this.getServletContext().setAttribute("gradeList",list);
            } else if("普通用户".equals(keyword)) {
                this.getServletContext().setAttribute("userList",list);
            } else if("一般管理员".equals(keyword)) {
                this.getServletContext().setAttribute("managerList",list);
            } else if("高级管理员".equals(keyword)) {
                this.getServletContext().setAttribute("superManagerList",list);
            } else if("文章板块".equals(keyword)) {
                this.getServletContext().setAttribute("partList",list);
            }
            response.sendRedirect("/blog/jsps/background/homepage/sortManage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

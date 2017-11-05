package com.xj.servlet;

import com.xj.domain.SystemDDL;
import com.xj.domain.User;
import com.xj.exceptions.DaoException;
import com.xj.service.RoleService;
import com.xj.service.SystemDDLService;
import com.xj.service.UserService;
import com.xj.service.impl.RoleServiceImpl;
import com.xj.service.impl.SystemDDLServiceImpl;
import com.xj.service.impl.UserServiceImpl;
import com.xj.util.LoggerTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhengguo on 2017/6/4.
 */
@WebServlet(name = "RoleServlet", urlPatterns = "/role")
public class RoleServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    private SystemDDLService systemDDLService = new SystemDDLServiceImpl();
    private UserService userService = new UserServiceImpl();
    private static LoggerTool logger = LoggerTool.newInstance(RoleServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("edit".equals(type)) {
            int grade = Integer.parseInt(request.getParameter("grade"));
            List<User> userList = null;
            try {
                userList = roleService.findUserListByGrade(grade);
            } catch (DaoException e) {
                logger.warn(e.getMessage());
            }
            String rolename = systemDDLService.findDdlname("角色类型", grade);
            List<SystemDDL> popedomList = systemDDLService.findElecSystemDDLByKeyWord(rolename);
            request.getSession().setAttribute("rolename", rolename);
            request.getSession().setAttribute("userList", userList);
            request.getSession().setAttribute("popedomList", popedomList);
            request.getRequestDispatcher("/jsps/background/homepage/roleManageEdit.jsp").forward(request,response);
            //response.sendRedirect("/blog/jsps/background/roleManageEdit.jsp");
        } else if ("update".equals(type)) {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            userService.updateGrade(user_id, 2);
            response.sendRedirect("/blog/jsps/background/homepage/roleManage.jsp");
        } else if ("delete".equals(type)) {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            userService.updateGrade(user_id, 1);
            response.sendRedirect("/blog/jsps/background/homepage/roleManage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

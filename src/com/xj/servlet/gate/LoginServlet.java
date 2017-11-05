package com.xj.servlet.gate;


import com.xj.domain.User;
import com.xj.service.UserService;
import com.xj.service.impl.UserServiceImpl;
import com.xj.util.MsgGenerateUtil;
import com.xj.util.ResponsePrintUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sheeran on 2017/6/11.
 * Talk is cheap show me code.
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        //check login
        if ("login".equals(type)) {
            UserService userService = new UserServiceImpl();
            Map<String, Object> map;
            String emailOrPhone = req.getParameter("user_phoneOrEmail");
            String password = req.getParameter("user_password");
            User user = userService.checkLoginUser(emailOrPhone, password);
            if (user.isNull()) {
                map = MsgGenerateUtil.getErrorMap("用户不存在或账号密码错误");
            } else if (user.getStatus() != User.STATUS_ACTIVE) {  //登陆失败
                map = MsgGenerateUtil.getErrorMap("该账号还未激活");
            } else {  //登陆成功
                req.getSession().setAttribute("user", user);   // store loginuser in session
                map = MsgGenerateUtil.getSuccessMap("登陆成功");
            }
            ResponsePrintUtil.print(resp, map);
        }
        //go loginPage
        else resp.sendRedirect("jsps/background/login.jsp");
    }


}

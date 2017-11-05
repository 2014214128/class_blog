package com.xj.servlet;

import com.xj.domain.User;
import com.xj.service.UserService;
import com.xj.service.impl.UserServiceImpl;
import com.xj.util.MsgGenerateUtil;
import com.xj.util.RequestParaseUtil;
import com.xj.util.ResponsePrintUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");

        if ("checkUserPhone".equals(type)) {
            String phone = req.getParameter("user_phone");
            if (userService.isPhoneExist(phone)) {
                returnErrorMes(resp, "手机号已经存在");
            }
            returnSuccessMes(resp);

        } else if ("checkUserEmail".equals(type)) {
            String email = req.getParameter("user_email");
            if (userService.isEmailExist(email)) {
                returnErrorMes(resp, "邮箱已经存在");
            }
            returnSuccessMes(resp);
        } else if ("register".equals(type)) {
            User user = (User) RequestParaseUtil.parse(req, User.class);
            userService.register(user);
            returnSuccessMes(resp);
        } else if ("resetPwd".equals(type)) {
            User user = (User) RequestParaseUtil.parse(req, User.class);
            userService.resetPassowrd(user.getPhone(), user.getPassword());
            returnSuccessMes(resp);
        } else if ("delete".equals(type)) {
            userService.deleteUser(Integer.parseInt(req.getParameter("user_id")));
            resp.sendRedirect("/blog/jsps/background/login.jsp");
        } else if ("logout".equals(type)) {
            if (req.getSession().getAttribute("user") == null) {
                return;
            }
            req.getSession().removeAttribute("user");
            req.getRequestDispatcher("/showPage?pageNow=1").forward(req, resp);
        } else if ("validate".equals(type)) {//将用户的状态更改为激活状态
            String uuid = req.getParameter("id");
            userService.active(uuid);
            req.setAttribute("mes","邮箱验证成功");
            req.getRequestDispatcher("jsps/background/register_success.jsp").forward(req, resp);
        } else if ("validateResetCode".equals(type)) {
            String uuid = req.getParameter("uuid");
            String code = String.valueOf(req.getSession().getAttribute("resetCode"));
            if (uuid.equals(code)) {
                returnSuccessMes(resp);
            }
            returnErrorMes(resp);
        }
    }

    private void returnErrorMes(HttpServletResponse resp) {
        returnErrorMes(resp, "");
    }

    private void returnErrorMes(HttpServletResponse resp, String mes) {
        Map<String, Object> map;
        map = MsgGenerateUtil.getErrorMap(mes);
        ResponsePrintUtil.print(resp, map);
    }

    private void returnSuccessMes(HttpServletResponse resp) {
        returnSuccessMes(resp, "");
    }

    private void returnSuccessMes(HttpServletResponse resp, String mes) {
        Map<String, Object> map;
        map = MsgGenerateUtil.getSuccessMap(mes);
        ResponsePrintUtil.print(resp, map);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

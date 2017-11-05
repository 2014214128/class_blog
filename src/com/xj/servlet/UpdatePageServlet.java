package com.xj.servlet;

import com.xj.domain.Picture;
import com.xj.domain.User;
import com.xj.service.PictureService;
import com.xj.service.UserService;
import com.xj.service.impl.PictureServiceImpl;
import com.xj.service.impl.UserServiceImpl;
import com.xj.util.LoggerTool;
import com.xj.util.RequestParaseUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sheeran on 2017/6/11.
 * Talk is cheap show me code.
 */
@WebServlet(name = "updatePageServlet", urlPatterns = "/updatePage")
public class UpdatePageServlet extends HttpServlet {
    private static LoggerTool logger = LoggerTool.newInstance(UpdatePageServlet.class);

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");

        if ("goUpdatePage".equals(type)) {
            User user = (User) req.getSession().getAttribute("user");
            int picture_id = user.getPicture_id();
            PictureService pictureService = new PictureServiceImpl();
            Picture picture = pictureService.getById(picture_id);
            user.setPicture(picture);
            //不需要进行查找，user应该在session中
            req.getRequestDispatcher("/blog/jsps/background/updatePersonalInfo.jsp").forward(req, resp);
        } else if ("modify".equals(type)) {

            ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
            servletFileUpload.setHeaderEncoding("UTF-8");
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) return;
            Picture picture = null;
            try {
                List<FileItem> items = servletFileUpload.parseRequest(req);
                for (FileItem item : items) {
                    if (!item.isFormField()) {   //上传头像操作
                        PictureService pictureService = new PictureServiceImpl();
                        picture = pictureService.uploadPicture(req, item);
                    } else {
                        String name = item.getFieldName();
                        String val = item.getString("utf-8");
                        RequestParaseUtil.parseFromItem(user, name, val);
                    }
                }
            } catch (Exception e) {
                logger.warn("从表单中提取属性出错" + e.getMessage());
            }
            UserService userService = new UserServiceImpl();
            if (picture == null) {
                //对未上传头像的用户进行更新
                userService.updatePersonInfo(user);
            }
            //对上传了头像的用户进行更新
            else {
                userService.updatePersonInfo(user, picture);
                user.setPicture(picture);
            }
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/blog/showPage?pageNow=1");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

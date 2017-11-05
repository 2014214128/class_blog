package com.xj.servlet;

import com.xj.dao.impl.DraftDaoImpl;
import com.xj.domain.Draft;
import com.xj.domain.Picture;
import com.xj.domain.User;
import com.xj.dto.PageBean;
import com.xj.service.DraftService;
import com.xj.service.PictureService;
import com.xj.service.SystemDDLService;
import com.xj.service.impl.DraftServiceImpl;
import com.xj.service.impl.PictureServiceImpl;
import com.xj.service.impl.SystemDDLServiceImpl;
import com.xj.util.LoggerTool;
import com.xj.util.RequestParaseUtil;
import com.xj.util.StringManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengguo on 2017/6/17.
 */
@WebServlet(name = "DraftServlet",urlPatterns = "/draft")
public class DraftServlet extends HttpServlet {
    private static LoggerTool logger = LoggerTool.newInstance(DraftServlet.class);
    private DraftService draftService = new DraftServiceImpl();
    private SystemDDLService systemDDLService = new SystemDDLServiceImpl();

    private static StringManager stringManager = StringManager.getInstance("com.xj.servlet");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if("show".equals(type)) {
            PageBean<Draft> pageBean = new PageBean<>(Integer.parseInt(request.getParameter("pageNow")), Integer.parseInt(stringManager.getString("draft.pageSize")));
            request.setAttribute("pageBean", draftService.showDraftsByPage(pageBean, ((User) request.getSession().getAttribute("user")).getId()));
            request.getRequestDispatcher("/jsps/background/homepage/draft.jsp").forward(request, response);
        } else if("delete".equals(type)) {
            draftService.deleteDraft(Integer.parseInt(request.getParameter("id")));
            PageBean<Draft> pageBean = new PageBean<>(Integer.parseInt(request.getParameter("pageNow")), Integer.parseInt(stringManager.getString("draft.pageSize")));
            request.setAttribute("pageBean", draftService.showDraftsByPage(pageBean, Integer.parseInt(request.getParameter("user_id"))));
            request.getRequestDispatcher("/jsps/background/homepage/draft.jsp").forward(request, response);
        } else if("edit".equals(type)) {
            Draft draft = draftService.findDraftById(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("draft", draft);
            if(draft.getCategory() > 0 ) {
                request.setAttribute("categoryname", systemDDLService.findDdlname("文章类型", draft.getCategory()));
            }
            if(draft.getSecond_category() > 0) {
                request.setAttribute("second_categoryname", systemDDLService.findDdlname("文章类别", draft.getSecond_category()));
            }
            request.getRequestDispatcher("/jsps/background/homepage/draftEdit.jsp").forward(request, response);
        } else if("save".equals(type)) {
            parseRequest(request,1);
            response.sendRedirect("jsps/background/homepage/publishArticle.jsp");
        } else if("update".equals(type)) {
            parseRequest(request,2);
            response.sendRedirect("jsps/background/homepage/publishArticle.jsp");
        }

    }

    @SuppressWarnings("Duplicates")
    private void parseRequest(HttpServletRequest request, int flag) {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        servletFileUpload.setHeaderEncoding("UTF-8");
        Draft draft = new Draft();
        User user = (User) request.getSession().getAttribute("user");
        draft.setUser_id(user.getId());
        Picture picture = null;
        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {  //上传照片
                    PictureService pictureService = new PictureServiceImpl();
                    picture = pictureService.uploadPicture(request, item);
                } else {                  //封装对象
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    RequestParaseUtil.parseFromItem(draft, name, value);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        draft.setTime(new Date());
        if(flag == 1) {
            if (picture == null)
                draftService.save(draft);
            else
                draftService.save(draft, picture);
        } else {
            draft.setId(((Draft) request.getSession().getAttribute("draft")).getId());
            if(picture == null)
                draftService.update(draft);
            else
                draftService.update(draft, picture);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

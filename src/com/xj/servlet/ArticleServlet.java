package com.xj.servlet;

import com.xj.domain.Article;
import com.xj.domain.Picture;
import com.xj.domain.User;
import com.xj.dto.PageBean;
import com.xj.service.ArticleService;
import com.xj.service.PictureService;
import com.xj.service.impl.ArticleServiceImpl;
import com.xj.service.impl.PictureServiceImpl;
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
import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
@WebServlet(name = "ArticleServlet", urlPatterns = "/article")
public class ArticleServlet extends HttpServlet {
    private static LoggerTool logger = LoggerTool.newInstance(ArticleServlet.class);
    private static StringManager stringManager = StringManager.getInstance("com.xj.servlet");

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        ArticleService articleService = new ArticleServiceImpl();
        if ("publish".equals(type)) {
            parseFormData(req);
            resp.sendRedirect("jsps/background/homepage/publishArticle.jsp");
        } else if ("show".equals(type)) {   //将所有文章均查出来
            PageBean<Article> pageBean = extractPageBean(req);
            pageBean = articleService.showArticleForManage(pageBean);
            req.setAttribute("pageBean", pageBean);
            //resp.sendRedirect("jsps/background/homepage/articleManage.jsp");
            req.getRequestDispatcher("/jsps/background/homepage/articleManage.jsp").forward(req, resp);
        } else if ("pass".equals(type)) {
            int id = Integer.parseInt(req.getParameter("id"));
            int part = Integer.parseInt(req.getParameter("part"));
            articleService.passArticle(id, part);
            PageBean<Article> pageBean = new PageBean<Article>(1,Integer.parseInt(stringManager.getString("article.pageSize")));
            pageBean = articleService.showArticleForManage(pageBean);
            req.setAttribute("pageBean", pageBean);
            //resp.sendRedirect("jsps/background/homepage/articleManage.jsp");
            req.getRequestDispatcher("/jsps/background/homepage/articleManage.jsp").forward(req, resp);
        } else if ("delete".equals(type)) {
            int id = Integer.parseInt(req.getParameter("id"));
            articleService.deleteById(id);
            PageBean<Article> pageBean = new PageBean<Article>(1,Integer.parseInt(stringManager.getString("article.pageSize")));
            pageBean = articleService.showArticleForManage(pageBean);
            req.setAttribute("pageBean", pageBean);
            //resp.sendRedirect("jsps/background/homepage/articleManage.jsp");
            req.getRequestDispatcher("/jsps/background/homepage/articleManage.jsp").forward(req, resp);
        } else if ("showDetail".equals(type)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Article article = articleService.showArticleDetail(id);
            req.setAttribute("article", article);
        } else if ("".equals(type)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Article article = articleService.getArticleById(id);
            req.setAttribute("article", article);
        } else if("search".equals(type)) {
            PageBean<Article> pageBean = extractPageBean(req);
            pageBean = articleService.findArticlesByKeyword1(req.getParameter("keyword"),pageBean);
            req.setAttribute("keyword",req.getParameter("keyword") );
            req.setAttribute("pageBean", pageBean);
            req.setAttribute("pageBeanRight", articleService.showArticlesByPage1(new PageBean<Article>(1, Integer.parseInt(stringManager.getString("article.pageSideSize")))));
            req.getRequestDispatcher("/jsps/findArticle.jsp").forward(req, resp);
        }
    }

    private PageBean<Article> extractPageBean(HttpServletRequest req) {
        int pageNow = Integer.parseInt(req.getParameter("pageNow"));
        int pageSize = Integer.parseInt(stringManager.getString("article.pageSize"));
        return new PageBean<>(pageNow, pageSize);
    }


    @SuppressWarnings({"unchecked", "Duplicates"})
    private Article parseFormData(HttpServletRequest req) {

        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        servletFileUpload.setHeaderEncoding("UTF-8");
        Article article = new Article();
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) return null;
        article.setUser_id(user.getId());
        Picture picture = null;
        try {
            List<FileItem> items = servletFileUpload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {  //上传照片
                    PictureService pictureService = new PictureServiceImpl();
                    picture = pictureService.uploadPicture(req, item);
                } else {                  //封装对象
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    RequestParaseUtil.parseFromItem(article, name, value);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        ArticleService articleService = new ArticleServiceImpl();
        if (picture == null)  //当没有上传照片
            articleService.publish(article);
        else  //上传了照片
            articleService.publish(article, picture);
        return article;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

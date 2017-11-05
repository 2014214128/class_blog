package com.xj.servlet;

import com.xj.domain.Article;
import com.xj.dto.PageBean;
import com.xj.service.ArticleService;
import com.xj.service.impl.ArticleServiceImpl;
import com.xj.util.LoggerTool;
import com.xj.util.StringManager;
import junit.framework.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sheeran on 2017/6/5.
 * Talk is cheap show me code.
 */
@WebServlet(name = "showPageServlet", urlPatterns = "/showPage")
public class ShowPageServlet extends HttpServlet {
    private static StringManager stringManager = StringManager.getInstance("com.xj.servlet");

    //主页面main.jsp跳转servlet  分页显示需要查找的信息
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        long start = System.currentTimeMillis();
        int pageNow = Integer.parseInt(req.getParameter("pageNow"));
        int pageSize = Integer.parseInt(stringManager.getString("article.pageSize"));
        int pageSideSize = Integer.parseInt(stringManager.getString("article.pageSideSize"));

        ArticleService articleService = new ArticleServiceImpl();

        PageBean<Article> pageBean = new PageBean<>(pageNow, pageSize);
        //查找所有的推荐文章
        pageBean = articleService.showArticleByPart(pageBean,1);
        req.setAttribute("pageBean", pageBean);

        PageBean<Article> pageBeanRight = new PageBean<>(1, pageSideSize);
        pageBeanRight = articleService.showArticlesByPage1(pageBeanRight);
        req.setAttribute("pageBeanRight", pageBeanRight);


        long time = System.currentTimeMillis() - start;
        LoggerTool.newInstance(ShowPageServlet.class).info("ShowPageServlet cost " + time + "ms  loading.......");
        req.getRequestDispatcher("/jsps/main.jsp").forward(req, resp);

    }

    @Test
    public void test() {
        Assert.assertEquals(10, Integer.parseInt(stringManager.getString("article.pageSize")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

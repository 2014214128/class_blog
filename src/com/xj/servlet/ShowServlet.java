package com.xj.servlet;

import com.xj.domain.Article;
import com.xj.dto.PageBean;
import com.xj.service.ArticleService;
import com.xj.service.impl.ArticleServiceImpl;
import com.xj.util.StringManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhengguo on 2017/6/22.
 */
@WebServlet(name = "ShowServlet",urlPatterns = "/show")
public class ShowServlet extends HttpServlet {
    private static StringManager stringManager = StringManager.getInstance("com.xj.servlet");
    private ArticleService articleService = new ArticleServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        if("life".equals(type)) {
            request.setAttribute("pageBean", articleService.showArticleByPart(new PageBean<Article>(Integer.parseInt(request.getParameter("pageNow")), Integer.parseInt(stringManager.getString("article.pageSize"))), 2));
            request.setAttribute("pageBeanRight", articleService.showArticlesByPage1(new PageBean<Article>(1, Integer.parseInt(stringManager.getString("article.pageSideSize")))));
            request.getRequestDispatcher("/jsps/life.jsp").forward(request, response);
        } else if("study".equals(type)) {
            request.setAttribute("pageBean", articleService.showArticleByPart(new PageBean<Article>(Integer.parseInt(request.getParameter("pageNow")), Integer.parseInt(stringManager.getString("article.pageSize"))), 3));
            request.setAttribute("pageBeanRight", articleService.showArticlesByPage1(new PageBean<Article>(1, Integer.parseInt(stringManager.getString("article.pageSideSize")))));
            request.getRequestDispatcher("/jsps/study.jsp").forward(request, response);
        } else if("show".equals(type)) {
            request.setAttribute("pageBeanRight", articleService.showArticlesByPage1(new PageBean<Article>(1, Integer.parseInt(stringManager.getString("article.pageSideSize")))));
            request.setAttribute("article", articleService.getArticleById(Integer.parseInt(request.getParameter("article_id"))));
            request.getRequestDispatcher("/jsps/articleShow.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

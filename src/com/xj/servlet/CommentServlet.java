package com.xj.servlet;

import com.xj.domain.Comment;
import com.xj.service.CommentService;
import com.xj.service.impl.CommentServiceImpl;
import com.xj.util.RequestParaseUtil;
import com.xj.util.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public class CommentServlet extends HttpServlet {
    private static StringManager stringManager = StringManager.getInstance("com.xj.servlet");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        if ("insert".equals(type)) {
            CommentService commentService = new CommentServiceImpl();
            Comment comment = (Comment) RequestParaseUtil.parse(req, Comment.class);
            commentService.insertComment(comment);
        } else if ("show".equals(type)) {
//            CommentService commentService = new CommentServiceImpl();
//            int article_id = Integer.parseInt(req.getParameter("id"));
//            int pageNow = Integer.parseInt(req.getParameter("pageNow"));
//            int pageSize = Integer.parseInt(stringManager.getString("comment.pageSize"));
//            PageBean<Comment> pageBean = new PageBean<>(pageNow, pageSize);
//            pageBean = commentService.showByPage(pageBean, article_id);
//            req.setAttribute("pageBean");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

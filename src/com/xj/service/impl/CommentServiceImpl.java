package com.xj.service.impl;

import com.xj.dao.CommentDao;
import com.xj.dao.UserDao;
import com.xj.dao.impl.CommentDaoImpl;
import com.xj.dao.impl.UserDaoImpl;
import com.xj.domain.Comment;
import com.xj.domain.User;
import com.xj.dto.PageBean;
import com.xj.exceptions.DaoException;
import com.xj.service.CommentService;
import com.xj.util.LoggerTool;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public class CommentServiceImpl implements CommentService {
    private static LoggerTool logger = LoggerTool.newInstance(CommentServiceImpl.class);

    @Override
    public void insertComment(Comment comment) {
        comment.setTime(new Date());
        CommentDao commentDao = new CommentDaoImpl();
        try {
            commentDao.addComment(comment);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }


    @Override
    public PageBean<Comment> showByPage(PageBean<Comment> pageBean, int article_id) {
        CommentDao commentDao = new CommentDaoImpl();
        int start = (pageBean.getPageNow() - 1) * pageBean.getPageSize();
        int end = pageBean.getPageNow() * pageBean.getPageSize();
        List<Comment> list = null;
        try {
            list = commentDao.listCommentsByPage(start, end, article_id);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        if (list == null)    //没有数据
            return null;
        UserDao userDao = new UserDaoImpl();
        Map<Integer, User> map = new HashMap<>();
        for (Comment comment : list) {     // who publish the comment ?
            User user = map.get(comment.getUser_id());
            if (user == null) {
                try {
                    user = userDao.queryById(comment.getUser_id());
                } catch (DaoException e) {
                    logger.warn(e.getMessage());
                }
            }
            comment.setUser(user);
            map.put(comment.getUser_id(), user);
        }
        return pageBean;
    }
}

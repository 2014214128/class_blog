package com.xj.dao;

import com.xj.domain.Comment;
import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public interface CommentDao {
    void addComment(Comment comment) throws DaoException;

    List<Comment> listCommentsByPage(int start, int end, int article_id) throws DaoException;
}

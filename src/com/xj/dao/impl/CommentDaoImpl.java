package com.xj.dao.impl;

import com.xj.dao.CommentDao;
import com.xj.domain.Comment;
import com.xj.exceptions.DaoException;
import com.xj.util.ResultTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {
    @Override
    public List<Comment> resultFrom(ResultSet rs) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        while (rs.next()) {
            Comment comment = (Comment) ResultTransformer.transform(Comment.class, rs);
            comments.add(comment);
        }
        return comments;
    }

    public void addComment(Comment comment) throws DaoException {
        String sql = "INSERT INTO comment(user_id, article_id, time, content) VALUES(?,?,?,?)";
        add(sql, comment);
    }

    @Override
    public List<Comment> listCommentsByPage(int start, int end, int article_id) throws DaoException {
        String sql = "SELECT * FROM comment WHERE article_id = ? limit ?,?";

        return queryBySql(sql, article_id, start, end);
    }


}

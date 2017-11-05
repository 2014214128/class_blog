package com.xj.service;


import com.xj.domain.Comment;
import com.xj.dto.PageBean;

import java.util.List;

/**
 * Created by sheeran on 2017/6/8.
 * Talk is cheap show me code.
 */
public interface CommentService  {
    void insertComment(Comment comment);

    PageBean<Comment> showByPage(PageBean<Comment> pageBean, int artilce_id);
}

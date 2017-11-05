package com.xj.service;

import com.xj.domain.Article;
import com.xj.domain.Picture;
import com.xj.dto.PageBean;

import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public interface ArticleService {

    void publish(Article article);

    void publish(Article article, Picture picture);

//    //使用单表查询进行分页查询
//    PageBean<Article> showArticlesByPage(PageBean<Article> pageBean);

    //使用多表连接进行分页查询
    PageBean<Article> showArticlesByPage1(PageBean<Article> pageBean);

    PageBean<Article> showArticleForManage(PageBean<Article> pageBean);

    //根据版块进行分页显示
    PageBean<Article> showArticleByPart(PageBean<Article> pageBean, int part);

    void deleteById(int id);

    void passArticle(int id, int part);

    Article getArticleById(int id);

    void setPart(int id);

    Article showArticleDetail(int id);

    PageBean<Article> findArticlesByKeyword(String keyword, PageBean<Article> pageBean);

    PageBean<Article> findArticlesByKeyword1(String keyword, PageBean<Article> pageBean);
}

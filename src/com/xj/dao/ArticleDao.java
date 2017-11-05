package com.xj.dao;

import com.xj.domain.Article;
import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public interface ArticleDao extends BaseDao<Article> {
    void addArticle(Article article) throws DaoException;

    int addArticlePipe(Article article) throws Exception;

    List<Article> listArticlesByPage(int pageNow, int pageSize) throws DaoException;

    int getCounts(int part);

    int getCounts();

    List<Article> listArticlesByPage1(int pageNow, int pageSize) throws DaoException;

    List<Article> listArticleForManage(int pageNow, int pageSize)  throws DaoException;

    void updateStatus(Article article) throws DaoException;

    void deleteArticleById(int id) throws DaoException;

    Article getArticleById(int id) throws DaoException;

    List<Article> listArticlesByPart(int pageNow, int pageSize, int part)  throws DaoException;

    List<Article> findArticlesByKeywords(int pageNow,int pageSize, String s) throws DaoException;

    List<Article> findArticlesByKeywords1(int pageNow,int pageSize, String s) throws DaoException;
}

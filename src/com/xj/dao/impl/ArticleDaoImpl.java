package com.xj.dao.impl;

import com.xj.dao.ArticleDao;
import com.xj.domain.Article;
import com.xj.domain.Picture;
import com.xj.domain.User;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTool;
import com.xj.util.JdbcTransactionTemplate;
import com.xj.util.ResultTransformer;
import com.xj.util.WordSegment;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao {
    public void addArticle(Article article) throws DaoException {
        String sql = "INSERT  INTO article (title,note,content,user_id,category,second_category,publish_time,picture_id)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        add(sql, article);
    }

    @Override
    public int addArticlePipe(Article article) throws Exception {
        String s1 = "INSERT  INTO article (title,note,content,user_id,category,second_category,publish_time,picture_id)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        JdbcTransactionTemplate.add(s1, article);
        ResultSet rs = JdbcTransactionTemplate.getPs().getGeneratedKeys();
        int article_id = 0;
        while (rs.next()) {
            article_id = rs.getInt(1);
        }
        return article_id;
    }

    public int getCounts() {
        String sql = "SELECT count(*) FROM article ";
        List<Object[]> list = executeQuery(sql);
        Number counts = (Number) list.get(0)[0];
        return counts.intValue();
    }


    @Override
    public List<Article> listArticlesByPage(int pageNow, int pageSize) throws DaoException {
        int start = (pageNow - 1) * pageSize;
        String sql = "SELECT * FROM article order by publish_time limit ?,? ";
        return queryBySql(sql, start, pageSize);
    }

    @Override
    public int getCounts(int part) {
        String sql = "SELECT count(*) FROM article WHERE part = ?";
        List<Object[]> list = executeQuery(sql,part);
        Number counts = (Number) list.get(0)[0];
        return counts.intValue();
    }


    public List<Article> listArticlesByPage1(int pageNow, int pageSize) throws DaoException {
        int start = (pageNow - 1) * pageSize;
        //按照左连接多表联结分页查询文章
        String sql = "select " +
                "t.*,u.loginname " +
                "from " +
                "(select " +
                "a.id," +
                "a.title," +
                "a.note," +
                "a.content," +
                "a.user_id," +
                "a.publish_time," +
                "p.url " +
                "from article a " +
                "left outer join picture p on a.picture_id = p.id where status = 1 ) t " +
                "left outer join user u " +
                "on t.user_id = u.id " +
                "order by t.publish_time  DESC limit" +
                " ?,? ;";
        Object[] args = {start, pageSize};
        return multiTableQuery(args, sql);
    }

    private List<Article> multiTableQuery(Object[] args, String sql) throws DaoException {
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> res = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                Picture picture = new Picture();
                User user = new User();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setNote(rs.getString("note"));
                article.setContent(rs.getString("content"));
                article.setPublish_time(rs.getDate("publish_time"));
                picture.setUrl(rs.getString("url"));
                user.setLoginname(rs.getString("loginname"));
                article.setUser(user);
                article.setPicture(picture);
                res.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            JdbcTool.closedAll(conn, ps, rs);
        }
        return res;
    }

    @Override
    public List<Article> listArticleForManage(int pageNow, int pageSize) throws DaoException {

        String sql = "select * from article order by publish_time desc limit ?,? ";
        int start = (pageNow - 1) * pageSize;
        return queryBySql(sql, start, pageSize);
    }

    @Override
    public void updateStatus(Article article) throws DaoException {
        String sql = "update article set part = ? , status = ? where id = ?";
        updateBySql(sql, new Object[]{article.getPart(), article.getStatus(), article.getId()});
    }

    @Override
    public void deleteArticleById(int id) throws DaoException {
        String sql = "delete from article where id = ? ";
        delete(sql, id);
    }

    @Override
    public Article getArticleById(int id) throws DaoException {
        String sql = "select * from article where id = ? ";
        List<Article> list = queryBySql(sql, id);
        if (list.size() > 1) throw new DaoException("文章id查询的文章不唯一");
        return list.get(0);
    }

    @Override
    public List<Article> listArticlesByPart(int pageNow, int pageSize, int part) throws DaoException {
        //连接article,user,picture 多表查询出文章的id,title,note,content,user_id,publish_time,picture_id, 限定article的版块
        String sql = "select t.*,u.loginname from (select a.id, a.title, a.note,a.content, a.user_id,a.publish_time,p.url " +
                "from article a left outer join picture p on a.picture_id = p.id  where a.part = ?) t left outer join user u " +
                "on t.user_id = u.id " +
                "order by t.publish_time  DESC limit" +
                " ?,? ;";
        int start = (pageNow - 1) * pageSize;
        Object[] args = {part, start, pageSize};
        return multiTableQuery(args, sql);
    }

    @Override
    public List<Article> findArticlesByKeywords(int pageNow, int pageSize, String s) throws DaoException {
        String sql = "SELECT a.*, MATCH(title,note,content)  AGAINST('"+ s +"' in boolean MODE) " +
                "as score FROM article a order by score DESC limit ?,?";
        int start = (pageNow - 1) * pageSize;
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<>();
        try {
            ps =  conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setNote(rs.getString("note"));
                article.setContent(rs.getString("content"));
                article.setPublish_time(rs.getDate("publish_time"));
                article.setUser_id(rs.getInt("user_id"));
                article.setPicture_id(rs.getInt("picture_id"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTool.closedAll(conn, ps, rs);
        }
        return articles;
    }

    @Override
    public List<Article> findArticlesByKeywords1(int pageNow, int pageSize, String s) throws DaoException {
        String  sql = "select * from article  where " + s + " order by publish_time desc limit ?,?";
        int start = (pageNow - 1) * pageSize;
        return queryBySql(sql, start, pageSize);
    }

    /*@Test
    public void test() {
        String s1 = "database程序员";
        String [] keywords = WordSegment.split(s1);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0 ; i < keywords.length ; i++) {
            stringBuffer.append(keywords[i] + " ");
        }
        String s = stringBuffer.toString().substring(0,stringBuffer.length()-1);
        try {
            List<Article> articles = findArticlesByKeywords(1,10, "database 程序员");
            for(Article a : articles) {
                System.out.println(a.getContent());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }*/
    @Test
    public void test() {
        String s1 = "database程序员";
        String [] keywords = WordSegment.split(s1);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0 ; i < keywords.length ; i++) {
            stringBuffer.append(" title like '%"+keywords[i]+"%' OR");
            stringBuffer.append(" note like '%"+keywords[i]+"%' OR");
            stringBuffer.append(" content like '%"+keywords[i]+"%' OR");
        }
        String s = stringBuffer.toString().substring(0,stringBuffer.length()-2);
        try {
            List<Article> articles = findArticlesByKeywords1(1,10, s);
            for(Article a : articles) {
                System.out.println(a.getContent());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> resultFrom(ResultSet rs) throws SQLException {
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            Article article = (Article) ResultTransformer.transform(Article.class, rs);
            articles.add(article);
        }
        return articles;
    }


}

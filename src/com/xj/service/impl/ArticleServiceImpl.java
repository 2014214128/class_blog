package com.xj.service.impl;

import com.xj.dao.ArticleDao;
import com.xj.dao.CommentDao;
import com.xj.dao.PictureDao;
import com.xj.dao.UserDao;
import com.xj.dao.impl.ArticleDaoImpl;
import com.xj.dao.impl.CommentDaoImpl;
import com.xj.dao.impl.PictureDaoImpl;
import com.xj.dao.impl.UserDaoImpl;
import com.xj.domain.Article;
import com.xj.domain.Comment;
import com.xj.domain.Picture;
import com.xj.dto.PageBean;
import com.xj.exceptions.DaoException;
import com.xj.service.ArticleService;
import com.xj.util.*;

import java.util.Date;
import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public class ArticleServiceImpl implements ArticleService {
    private static StringManager stringManager = StringManager.getInstance("com.xj.service.impl");
//    private static int count = Runtime.getRuntime().availableProcessors();
//    private static final ExecutorService service = Executors.newFixedThreadPool(2 * (count + 1));

    private LoggerTool logger = LoggerTool.newInstance(this.getClass());

//    public static ExecutorService getService() {
//        return service;
//    }

    public void publish(Article article) {
        setDefaultPublishTime(article);
        setDefaultPicture(article);
        ArticleDao dao = new ArticleDaoImpl();
        try {
            dao.addArticle(article);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }

    private void setDefaultPublishTime(Article article) {
        article.setPublish_time(new Date());
    }

    private void setDefaultPicture(Article article) {
        article.setPicture_id(Integer.parseInt(stringManager.getString("default_picture_id")));
    }

    /**
     * 存储带有图片的文章
     *
     * @param article 文章
     * @param picture 图片
     */
    public void publish(Article article, Picture picture) {
        JdbcTool.beginTransaction();
        try {
            ArticleDao articleDao = new ArticleDaoImpl();
            PictureDao pictureDao = new PictureDaoImpl();
            int picture_id = pictureDao.addPicturePipe(picture);

            setDefaultPublishTime(article);
            article.setPicture_id(picture_id);
            articleDao.addArticlePipe(article);
            JdbcTool.endTransaction();
        } catch (Exception e) {
            JdbcTool.rollBackTrasaction();
            logger.warn("发布文章出错" + e.getMessage());
        } finally {
            JdbcTool.closedPreparedStatement(JdbcTransactionTemplate.getPs());
            JdbcTool.closedConnection(JdbcTransactionTemplate.getConn());
        }
    }

    public PageBean<Article> showArticlesByPage1(PageBean<Article> pageBean) {
        ArticleDao articleDao = new ArticleDaoImpl();
        List<Article> articles = null;
        try {
            articles = articleDao.listArticlesByPage1(pageBean.getPageNow(), pageBean.getPageSize());
        } catch (DaoException e) {
            logger.warn("分页显示文章出错" + e.getMessage());
        }
        pageBean.setData(articles);
        pageBean.setRowCount(articleDao.getCounts());
        return pageBean;
    }

    @Override
    public PageBean<Article> showArticleForManage(PageBean<Article> pageBean) {
        ArticleDao articleDao = new ArticleDaoImpl();
        List<Article> articles = null;
        try {
            articles = articleDao.listArticleForManage(pageBean.getPageNow(), pageBean.getPageSize());
        } catch (DaoException e) {
            logger.warn("分页显示管理文章出错" + e.getMessage());
        }
        pageBean.setData(articles);
        pageBean.setRowCount(articleDao.getCounts());
        return pageBean;
    }

    @Override
    public PageBean<Article> showArticleByPart(PageBean<Article> pageBean, int part) {
        ArticleDao articleDao = new ArticleDaoImpl();
        List<Article> articles = null;
        try {
            articles = articleDao.listArticlesByPart(pageBean.getPageNow(), pageBean.getPageSize(), part);
        } catch (DaoException e) {
            logger.warn("分页显示管理文章出错" + e.getMessage());
        }
        pageBean.setData(articles);
        pageBean.setRowCount(articleDao.getCounts(part));
        return pageBean;
    }

    public void passArticle(int id , int part) {
        Article article = new Article();
        article.setId(id);
        article.setPart(part);
        article.setStatus(Article.STATUS_PASS);
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
            articleDao.updateStatus(article);
        } catch (DaoException e) {
            logger.warn("审批通过文章出错" + e.getMessage());
        }
    }

    public Article getArticleById(int id) {
        ArticleDao articleDao = new ArticleDaoImpl();
        UserDao userDao = new UserDaoImpl();
        Article article = new Article();
        try {
            article = articleDao.getArticleById(id);
            article.setUser(userDao.queryById(article.getUser_id()));
        } catch (DaoException e) {
            logger.warn("根据有文章id查询出错" + e.getMessage());
        }
        return article;
    }

    //设置文章的版块
    @Override
    public void setPart(int id) {

    }

    @Override
    public Article showArticleDetail(int id) {
        ArticleDao articleDao = new ArticleDaoImpl();
        Article article = new Article();
        try {
            article = articleDao.getArticleById(id);
            CommentDao commentDao = new CommentDaoImpl();

            List<Comment> comments = commentDao.listCommentsByPage(0, 10, article.getId());
            article.setComments(comments);

        } catch (DaoException e) {
            logger.warn("显示文章细节查找出错" + e.getMessage());
        }
        return article;
    }

    @Override
    public PageBean<Article> findArticlesByKeyword(String keyword, PageBean<Article> pageBean) {
        ArticleDao articleDao = new ArticleDaoImpl();
        String [] keywords = WordSegment.split(keyword);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0 ; i < keywords.length ; i++) {
            stringBuffer.append(keywords[i] + " ");
        }
        String s = stringBuffer.toString().substring(0,stringBuffer.length()-1);
        List<Article> articles = null;
        try {
            articles = articleDao.findArticlesByKeywords(pageBean.getPageNow(), pageBean.getPageSize(),s);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        pageBean.setData(articles);
        return pageBean;
    }
    @Override
    public PageBean<Article> findArticlesByKeyword1(String keyword, PageBean<Article> pageBean) {
        ArticleDao articleDao = new ArticleDaoImpl();
        String [] keywords = WordSegment.split(keyword);
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0 ; i < keywords.length ; i++) {
            stringBuffer.append(" title like '%"+keywords[i]+"%' OR");
            stringBuffer.append(" note like '%"+keywords[i]+"%' OR");
            stringBuffer.append(" content like '%"+keywords[i]+"%' OR");
        }
        String s = stringBuffer.toString().substring(0,stringBuffer.length() - 2);
        List<Article> articles = null;
        try {
            articles = articleDao.findArticlesByKeywords1(pageBean.getPageNow(), pageBean.getPageSize(),s);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        pageBean.setData(articles);
        return pageBean;
    }



    @Override
    public void deleteById(int id) {
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
            articleDao.deleteArticleById(id);
        } catch (DaoException e) {
            logger.warn("删除文章失败" + e.getMessage());
        }
    }

//    @Override
//    public PageBean<Article> showArticlesByPage(PageBean<Article> pageBean) {
//        ArticleDao articleDao = new ArticleDaoImpl();
//        int pageNow = pageBean.getPageNow();
//        int pageSize = pageBean.getPageSize();
//        List<Article> articles = null;
//        try {
//            articles = articleDao.listArticlesByPage(pageNow, pageSize);
//        } catch (DaoException e) {
//            logger.warn(e.getMessage());
//        }
//        if (articles == null)
//            return null;
//
//        //尝试使用多线程来优化
//        CountDownLatch countDownLatch = new CountDownLatch(2 * count);
//        //查找关联用户
//        for (int i = 0, len = articles.size(); i < count; i++) {
//            int size = len / count;
//            List<Article> subArticles = (i == count - 1) ? articles.subList(i * size, len) : articles.subList(i * size, (i + 1) * size);
//            service.execute(new QueryUserThread(subArticles, countDownLatch));
//            service.execute(new QueryPictureThread(subArticles, countDownLatch));
//            pageBean.getData().addAll(subArticles);
//        }
//        //查找关联的图片
//
//        //查找关联的级别
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            logger.warn("多线程获取文章相关信息失败");
//        }
//        int rowCount = articleDao.getCounts();
//        pageBean.setData(articles);
//        pageBean.setRowCount(rowCount);
//        return pageBean;
//    }


    //开辟查找用户线程
//    private Map<Integer, User> map = new HashMap<>();
//
//    class QueryUserThread implements Runnable {
//        List<Article> articles;
//        CountDownLatch countDownLatch;
//
//        QueryUserThread(List<Article> articles, CountDownLatch countDownLatch) {
//            this.articles = articles;
//            this.countDownLatch = countDownLatch;
//        }
//
//        @Override
//        public void run() {
//            for (Article article : articles) {
//                User user = map.get(article.getUser_id());
//                if (user == null) {
//                    UserDao userDao = new UserDaoImpl();
//                    try {
//                        user = userDao.getById(article.getUser_id());
//                    } catch (DaoException e) {
//                        logger.warn(e.getMessage());
//                    }
//                }
//                article.setUser(user);
//                map.put(article.getUser_id(), user);
//            }
//            countDownLatch.countDown();
//        }
//    }
//
//
//    //开辟查找照片线程
//    class QueryPictureThread implements Runnable {
//        List<Article> articles;
//        CountDownLatch countDownLatch;
//
//        QueryPictureThread(List<Article> articles, CountDownLatch countDownLatch) {
//            this.articles = articles;
//            this.countDownLatch = countDownLatch;
//        }
//
//        @Override
//        public void run() {
//            for (Article article : articles) {
//                if (article.getPicture_id() == -1) continue;
//                PictureDao pictureDao = new PictureDaoImpl();
//                Picture picture = null;
//                try {
//                    picture = pictureDao.getById(article.getPicture_id());
//                } catch (DaoException e) {
//                    logger.warn(e.getMessage());
//                }
//                article.setPicture(picture);
//            }
//            countDownLatch.countDown();
//        }
//    }

}

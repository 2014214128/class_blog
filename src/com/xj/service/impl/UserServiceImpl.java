package com.xj.service.impl;

import com.xj.dao.PictureDao;
import com.xj.dao.UserDao;
import com.xj.dao.impl.PictureDaoImpl;
import com.xj.dao.impl.UserDaoImpl;
import com.xj.domain.Picture;
import com.xj.domain.User;
import com.xj.exceptions.DaoException;
import com.xj.mail.MailSender;
import com.xj.service.PictureService;
import com.xj.service.UserService;
import com.xj.util.JdbcTool;
import com.xj.util.JdbcTransactionTemplate;
import com.xj.util.LoggerTool;
import com.xj.util.Md5Util;
import org.apache.commons.mail.EmailException;

import java.net.MalformedURLException;
import java.util.UUID;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    private static LoggerTool logger = LoggerTool.newInstance(UserServiceImpl.class);

    @Override
    public boolean isEmailExist(String email) {
        User user = new User();
        try {
            user = userDao.queryByEmail(email);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        return !user.isNull();
    }

    @Override
    public boolean isPhoneExist(String phone) {
        User user = new User();
        try {
            user = userDao.queryByPhone(phone);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        return !user.isNull();
    }

    public User checkLoginUser(String emailOrPhone, String password) {
        password = Md5Util.doMD5(password);
        User user = new User();
        try {
            if (isEmail(emailOrPhone)) {
                user = userDao.getLoginUserByEmail(emailOrPhone, password);
            } else {
                user = userDao.getLoginUserByPhone(emailOrPhone, password);
            }
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        //获取用户的头像信息
        PictureService pictureService = new PictureServiceImpl();
        Picture picture = pictureService.getById(user.getPicture_id());
        user.setPicture(picture);
        return user;
    }

    public void register(User user) {
        //设置验证码
        String uuid = UUID.randomUUID().toString();
        user.setUuid(uuid);
        //设置用户等级为1
        user.setGrade(1);
        user.setPassword(Md5Util.doMD5(user.getPassword()));
        try {
            userDao.addUser(user);
            //发送邮件
            sendRegisterEmail(user.getEmail(), uuid);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }


    public void resetPassowrd(String phone, String newpassword) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(Md5Util.doMD5(newpassword));
        try {
            userDao.updatePassword(user);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }


    @Override
    public void updatePersonInfo(User user) {
        try {
            userDao.updatePersonalInfo(user);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void updatePersonInfo(User user, Picture picture) {
        JdbcTool.beginTransaction();
        PictureDao pictureDao = new PictureDaoImpl();
        try {
            int picture_id = pictureDao.addPicturePipe(picture);
            user.setPicture_id(picture_id);
            userDao.updateUserPipe(user);
            JdbcTool.endTransaction();
        } catch (Exception e) {
            JdbcTool.rollBackTrasaction();
            logger.warn("更新带头像的用户信息出错" + e.getMessage());
        } finally {
            JdbcTool.closedPreparedStatement(JdbcTransactionTemplate.getPs());
            JdbcTool.closedConnection(JdbcTransactionTemplate.getConn());
        }
    }

    @Override
    public void updateGrade(int user_id, int grade) {
        try {
            userDao.updateGrade(user_id, grade);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int user_id) {
        User user = new User();
        user.setId(user_id);
        try {
            userDao.deleteUser(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void active(String uuid) {
        try {
            User user = userDao.queryByUUID(uuid);
            if (user == null) {
                return;
            }
            user.setUuid(null);
            user.setStatus(User.STATUS_ACTIVE);
            userDao.updateStatus(user);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }


    public void sendResetEmail(String email, String uuid) {
        String sub = "信一博客:密码重置";
        String mes = "尊敬的用户，您好，你正在尝试重置密码，为了确保您的账号安全，请点击下面的连接进行验证" +
                "<a href='http://localhost:8080/blog/user?type=validateRegisterCode&id=" + uuid + "'></a>";
        try {
            MailSender.sendHtmlMail(email, sub, mes);
        } catch (EmailException | MalformedURLException e) {
            logger.warn(e.getMessage());
        }
    }

    private void sendRegisterEmail(String email, String uuid) {
        String sub = "信一博客注册邮箱验证";
        String mes = "<html> 尊敬的用户您好，欢迎注册信一博客，请点击下面的连接进行验证"
                + "<a href='http://www.xinyiblog.xin/blog/user?type=validate&id=" + uuid + "'>www.xinyiblog.xin?id=" + uuid + "</a></html>";
        try {
            MailSender.sendHtmlMail(email, sub, mes);
        } catch (EmailException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private boolean isEmail(String eop) {
        int at = eop.indexOf('@');
        if (at > 0) return true;
        return false;
    }

}

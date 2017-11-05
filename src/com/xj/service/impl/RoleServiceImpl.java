package com.xj.service.impl;

import com.xj.dao.UserDao;
import com.xj.dao.impl.UserDaoImpl;
import com.xj.domain.User;
import com.xj.exceptions.DaoException;
import com.xj.service.RoleService;
import com.xj.util.LoggerTool;

import java.util.List;

/**
 * Created by Asus on 2017/6/4.
 */
public class RoleServiceImpl implements RoleService {
    private UserDao userDao = new UserDaoImpl();
    private static LoggerTool logger = LoggerTool.newInstance(RoleServiceImpl.class);

    @Override
    public List<User> findUserListByGrade(int grade) {
        try {
            return userDao.listUsersByGrade(grade);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}
package com.xj.dao;

import com.xj.domain.User;
import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public interface UserDao extends BaseDao<User> {
    void addUser(User user) throws DaoException;

    void deleteUser(User user) throws DaoException;

    User queryByPhone(String phone) throws DaoException;

    User queryByEmail(String email) throws DaoException;

    User queryById(int user_id) throws DaoException;

    List<User> listUsersByGrade(int grade) throws DaoException;

    void updateGrade(int user_id, int grade) throws DaoException;

    void updatePassword(User user) throws DaoException;

    void updatePersonalInfo(User user) throws DaoException;

    User getLoginUserByEmail(String email, String password) throws DaoException;

    User getLoginUserByPhone(String phone, String password) throws DaoException;

    User queryByUUID(String uuid) throws DaoException;

    void updateUserPipe(User user) throws Exception;

    void updateStatus(User user) throws DaoException;
}

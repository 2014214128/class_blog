package com.xj.service;

import com.xj.domain.User;
import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by Asus on 2017/6/4.
 */
public interface RoleService
{
    List<User> findUserListByGrade(int grade) throws DaoException;
}

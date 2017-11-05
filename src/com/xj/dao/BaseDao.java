package com.xj.dao;

import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public interface BaseDao<T> {
    List<T> queryBySql(String sql, Object... args) throws DaoException;

    void add(String sql, T t) throws DaoException;

    void delete(String sql, Object... args) throws DaoException;

    void updateBySql(String sql, Object[] args) throws DaoException;
}

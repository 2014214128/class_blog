package com.xj.dao.impl;

import com.xj.dao.BaseDao;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTool;
import com.xj.util.PsTransformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sheeran on 2017/6/1.
 * Talk is cheap show me code.
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    public List<T> queryBySql(String sql, Object... args) throws DaoException {
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            list = resultFrom(rs);
            if (list.isEmpty()) return Collections.EMPTY_LIST;

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            JdbcTool.closedAll(conn, ps, rs);
        }
        return list;
    }

    //将每一行的数据封装到一个对象数组，然后将这些数组存放在集合中
    public static List<Object[]> executeQuery(String sql, Object... Parameters) {
        List<Object[]> all = new ArrayList<>();
        Connection ct = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ct = JdbcTool.openConnection();
            ps = ct.prepareStatement(sql);
            if (Parameters != null) {
                for (int i = 0; i < Parameters.length; i++) {
                    ps.setObject(i + 1, Parameters[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            // 获得rs的总列数
            int column = resultSetMetaData.getColumnCount();
            // 将rs中的每一行的对象的封装到objects中，再将objects封装到all中
            while (rs.next()) {
                Object[] objects = new Object[column];
                for (int i = 0; i < column; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                all.add(objects);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询方法出错");
        } finally {
            JdbcTool.closedAll(ct, ps, rs);
        }
        return all;
    }

    public void add(String sql, T t) throws DaoException {
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            PsTransformer.setSqlParamters(sql, t, ps);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage());
        } finally {
            JdbcTool.closedPreparedStatement(ps);
            JdbcTool.closedConnection(conn);
        }
    }


    public void delete(String sql, Object... args) throws DaoException {
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        } finally {
            JdbcTool.closedPreparedStatement(ps);
            JdbcTool.closedConnection(conn);
        }
    }


    public void updateBySql(String sql, Object[] args) throws DaoException {
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            JdbcTool.closedPreparedStatement(ps);
            JdbcTool.closedConnection(conn);
        }

    }

    public abstract List<T> resultFrom(ResultSet rs) throws SQLException;
}

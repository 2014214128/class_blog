package com.xj.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.xj.util.JdbcTool.currentConnection;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public class JdbcTransactionTemplate {
    //fixme 这里可能产生同步的问题
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    //抽离出一个操作和关闭分离的方法，便于进行事务操作
    public static void add(String sql, Object obj) throws Exception {
        conn = currentConnection();
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        PsTransformer.setSqlParamters(sql, obj, ps);
        ps.execute();
    }

    public static void update(String sql, Object[] args) throws Exception {
        conn = currentConnection();
        ps = conn.prepareStatement(sql);
        for (int i = 0, len = args.length; i < len; i++) {
            ps.setObject(i + 1, args[i]);
        }
        ps.execute();
    }


    public static Connection getConn() {
        return conn;
    }

    public static PreparedStatement getPs() {
        return ps;
    }

    public static ResultSet getRs() {
        return rs;
    }
}

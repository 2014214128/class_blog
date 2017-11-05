package com.xj.util;


import java.sql.*;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public class JdbcTool {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "615615";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://121.251.19.130:3306/blog?userunicode=true&characterEncoding=utf-8";
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection currentConnection() {
        Connection connection = null;
        connection = threadLocal.get();
        try {
            if (connection == null || connection.isClosed()) {
                connection = openConnection();
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closedAll(Connection connection, PreparedStatement prepareStatement, ResultSet resultSet) {
        closedResultSet(resultSet);
        closedPreparedStatement(prepareStatement);
        closedConnection(connection);
    }

    public static void closedConnection(Connection connection) {
        try {
            if (connection != null) {
                if (connection.isClosed()) return;
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closedPreparedStatement(PreparedStatement preparedStatememt) {
        try {
            if (preparedStatememt != null) {
                if (preparedStatememt.isClosed()) return;
                preparedStatememt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closedResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                if (resultSet.isClosed()) return;
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void beginTransaction() {
        Connection conn = currentConnection();
        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);  //脏读
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void endTransaction() {
        Connection conn = currentConnection();
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBackTrasaction();
        }
    }

    public static void rollBackTrasaction() {
        Connection conn = currentConnection();
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

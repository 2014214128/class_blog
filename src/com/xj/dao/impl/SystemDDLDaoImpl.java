package com.xj.dao.impl;

import com.xj.dao.SystemDDLDao;
import com.xj.domain.SystemDDL;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTool;
import com.xj.util.ResultTransformer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/6/2.
 */
public class SystemDDLDaoImpl extends BaseDaoImpl<SystemDDL> implements SystemDDLDao{

    @Override
    public List<SystemDDL> resultFrom(ResultSet rs) throws SQLException {
        List<SystemDDL> systemDDLList = new ArrayList<>();
        SystemDDL systemDDL = null;
        while(rs.next()) {
            systemDDL = (SystemDDL) ResultTransformer.transform(SystemDDL.class, rs);
            systemDDLList.add( systemDDL);
        }
        return systemDDLList;
    }


    @Override
    public void addSystemDDL(SystemDDL systemDDL) throws DaoException {
        String sql = "insert into systemddl(keyword,ddlcode,ddlname) values (?,?,?)";
        add(sql, systemDDL);
    }

    @Override
    public List<Object> findKeyword() {
        String sql = "select distinct s.keyword from systemddl s";
        List<Object> list = new ArrayList<Object>();
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("keyword"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTool.closedAll(conn, ps, rs);
        }
        return  list;
    }

    @Override
    public List<SystemDDL> findSystemDDLListByCondition(String keyword) throws DaoException {
        String sql = "select * from systemddl where keyword = ? order by ddlcode asc";
        return  queryBySql(sql,keyword);
    }

    @Override
    public void deleteSystemDDLList(List<SystemDDL> systemDDLS) {
        for(SystemDDL s : systemDDLS) {
            deleteSystemDDL(s);
        }
    }

    @Override
    public String findDdlname(String keyword, int ddlcode) throws DaoException {
        String sql = "select * from systemddl where keyword = ? and ddlcode = ?";
        return queryBySql(sql, keyword , ddlcode).get(0).getDdlname();
    }

    private void deleteSystemDDL(SystemDDL s) {
        String sql = "delete from systemddl where id = ?";
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTool.closedConnection(conn);
            JdbcTool.closedPreparedStatement(ps);
        }
    }


    /*@Test
    public void test() {
        SystemDDLDaoImpl systemDDLDaoImpl = new SystemDDLDaoImpl();
        List<Object> list = findKeyword();
        for(Object o : list) {
            System.out.println(o.toString());
        }
    }*/
    /*@Test
    public void testadd() {
        SystemDDLDaoImpl systemDDLDaoImpl = new SystemDDLDaoImpl();
        SystemDDL systemDDL = new SystemDDL();
        systemDDL.setKeyword("文章类别");
        systemDDL.setDdlcode(4);
        systemDDL.setDdlname("jsp");u
        systemDDLDaoImpl.addSystemDDL(systemDDL);
    }*/
//    @Test
//    public void testFindSystemDDLListByCondition() {
//        SystemDDLDaoImpl systemDDLDaoImpl = new SystemDDLDaoImpl();
//        String keyword = "文章类别";
//        List<SystemDDL> list =  systemDDLDaoImpl.findSystemDDLListByCondition(keyword);
//        for(SystemDDL s : list) {
//            System.out.println(s.getDdlname());
//        }
//    }

    /*@Test
    public void testFindDdlname() {
        SystemDDLDaoImpl systemDDLDaoImpl = new SystemDDLDaoImpl();
        System.out.println(systemDDLDaoImpl.findDdlname("角色类型",2));
    }*/

}

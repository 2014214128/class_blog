package com.xj.dao.impl;

import com.xj.dao.BaseDao;
import com.xj.dao.DraftDao;
import com.xj.domain.Draft;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTool;
import com.xj.util.ResultTransformer;
import org.junit.Test;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhengguo on 2017/6/17.
 */
public class DraftDaoImpl extends BaseDaoImpl<Draft> implements DraftDao{
    @Override
    public void addDraft(Draft draft) {
        String sql = "INSERT  INTO draft (title,note,content,user_id,category,second_category,time,picture_id)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try {
            add(sql, draft);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDraft(int  id) {
        String sql = "delete FROM draft where id = ?";
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTool.closedConnection(conn);
            JdbcTool.closedPreparedStatement(ps);
        }

    }

    @Override
    public Draft findDraftById(int id) {
        String sql = "select * from draft where id = ? ";
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Draft draft = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                draft = new Draft();
                draft.setId(id);
                draft.setTitle(rs.getString("title"));
                draft.setNote(rs.getString("note"));
                draft.setContent(rs.getString("content"));
                draft.setCategory(rs.getInt("category"));
                draft.setSecond_category(rs.getInt("second_category"));
                draft.setTime(rs.getDate("time"));
                draft.setPicture_id(rs.getInt("picture_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return draft;
    }

    @Override
    public void updateDraft(Draft draft) {
        String sql = "UPDATE  draft SET title = ?,note = ?,content = ?,user_id = ?,category = ?,second_category = ?,time = ?,picture_id = ?"
                + " WHERE id = ?";
        /*try {
            updateBySql(sql, draft);
        } catch (DaoException e) {
            e.printStackTrace();
        }*/
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, draft.getTitle());
            ps.setString(2, draft.getNote());
            ps.setString(3, draft.getContent());
            ps.setInt(4, draft.getUser_id());
            ps.setInt(5, draft.getCategory());
            ps.setInt(6, draft.getSecond_category());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            ps.setString(7, simpleDateFormat.format(new Date()));
            ps.setInt(8, draft.getPicture_id());
            ps.setInt(9, draft.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Draft> listDraftsByPage(int id, int pageNow, int pageSize) {
        int start = (pageNow - 1) * pageSize;
        String sql = "select * from draft where user_id = ? order by time DESC limit ?,?";
        Connection conn = JdbcTool.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Draft> drafts = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, start);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            Draft draft = null;
            while(rs.next()) {
                draft = new Draft();
                draft.setId(rs.getInt("id"));
                draft.setTitle(rs.getString("title"));
                draft.setTime(rs.getDate("time"));
                drafts.add(draft);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTool.closedAll(conn, ps, rs);
        }
        return drafts;
    }
    @Override
    public int getCounts(int id) {
        String sql = "SELECT count(*) FROM draft where user_id = ? ";
        List<Object[]> list = executeQuery(sql, id);
        Number counts = (Number) list.get(0)[0];
        return counts.intValue();
    }

    @Override
    public List<Draft> resultFrom(ResultSet rs) throws SQLException {
        List<Draft> drafts = new ArrayList<>();
        while (rs.next()) {
            Draft draft = (Draft) ResultTransformer.transform(Draft.class, rs);
            drafts.add(draft);
        }
        return drafts;
    }

    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();

        System.out.println(draftDao.listDraftsByPage(4,1,10).size());
        for(Draft draft : draftDao.listDraftsByPage(4,1,10)) {
            System.out.println(draft.getId());
        }

    }*/
    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();

        System.out.println(draftDao.getCounts(4));

    }*/
    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();
        draftDao.deleteDraft(3);
    }*/
    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();
        Draft draft = draftDao.findDraftById(1);
        System.out.println(draft.getTitle());
    }*/
    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();
        Draft draft = new Draft();
        draft.setTitle("1111111111111111");
        draft.setNote("1111111111111111111111");
        draft.setContent("11111111111111111111111111111111111111111111");
        draft.setUser_id(4);
        draft.setCategory(1);
        draft.setSecond_category(2);
        draft.setTime(new Date());
        draftDao.addDraft(draft);
    }*/
    /*@Test
    public void test() {
        DraftDaoImpl draftDao = new DraftDaoImpl();
        Draft draft = new Draft();
        draft.setId(1);
        draft.setTitle("1111111111111111");
        draft.setNote("1111111111111111111111");
        draft.setContent("11111111111111111111111111111111111111111111");
        draft.setUser_id(4);
        draft.setCategory(1);
        draft.setSecond_category(2);
        draftDao.updateDraft(draft);
    }*/
}

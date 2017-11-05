package com.xj.dao.impl;

import com.xj.dao.UserDao;
import com.xj.domain.User;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTransactionTemplate;
import com.xj.util.ResultTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/5/28.
 * Talk is cheap show me code.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public void updatePassword(User user) throws DaoException {
        String sql = "UPDATE  user SET password = ? where phone = ?";
        updateBySql(sql, new Object[]{user.getPassword(), user.getPhone()});
    }

    public void deleteUser(User user) throws DaoException {
        String sql = "DELETE FROM user WHERE id = ?";
        delete(sql, user.getId());
    }

    public User queryByPhone(String phone) throws DaoException {
        List<User> list = queryBySql("SELECT * FROM user where phone = ? ", phone);
        return extractUser(list);
    }

    public User queryByEmail(String email) throws DaoException {
        List<User> list = queryBySql("SELECT * FROM user where email = ?", email);
        return extractUser(list);
    }

    @Override
    public List<User> listUsersByGrade(int grade) throws DaoException {
        return queryBySql("select * from user where grade = ?", grade);
    }

    @Override
    public void updateGrade(int user_id, int grade) throws DaoException {
        String sql = "update user set grade = ? where id = ?";
        updateBySql(sql, new Object[]{grade, user_id});
    }

    public User queryById(int user_id) throws DaoException {
        String sql = "SELECT id,name,loginname from user where id = ? ";
        List<User> list = queryBySql(sql, user_id);
        return extractUser(list);
    }

    @Override
    public void updatePersonalInfo(User user) throws DaoException {
        String sql = "UPDATE user set name= ?,sex=?,phone=?,email=?,birthday=?,address=?,loginname=?" +
                "where id = ?";
        Object[] args = {user.getName(), user.getSex(), user.getPhone(), user.getEmail(), user.getBirthday(),
                user.getAddress(), user.getLoginname(), user.getId()};
        updateBySql(sql, args);
    }


    @Override
    public User getLoginUserByEmail(String email, String password) throws DaoException {
        String sql = "SELECT * FROM user where email = ? and password = ?";

        List<User> list = queryBySql(sql, email, password);
        return extractUser(list);
    }

    @Override
    public User getLoginUserByPhone(String phone, String password) throws DaoException {
        String sql = "SELECT * FROM user where phone = ? and password =?";
        List<User> list = queryBySql(sql, phone, password);
        return extractUser(list);
    }

    @Override
    public User queryByUUID(String uuid) throws DaoException {
        String sql = "SELECT * FROM user where uuid = ?";
        List<User> list = queryBySql(sql, uuid);
        return extractUser(list);
    }

    /**
     * 在事务机制下对用户进行更新信息
     */
    @Override
    public void updateUserPipe(User user) throws Exception {
        String sql = "update user set name=?,sex=?,phone=?,email=?,birthday=?,address=?,loginname=?,picture_id=? " +
                "where id = ?";
        Object[] args = {user.getName(), user.getSex(), user.getPhone(), user.getEmail(), user.getBirthday(),
                user.getBirthday(), user.getLoginname(), user.getPicture_id(), user.getId()};
        JdbcTransactionTemplate.update(sql, args);
    }

    @Override
    public void updateStatus(User user) throws DaoException {
        String sql = "update user set uuid = null,status=1 where id = ?";
        updateBySql(sql, new Object[]{user.getId()});
    }

    public void addUser(User user) throws DaoException {
        String sql = "insert into " +
                "user (name,password,sex,phone,email,birthday,address,loginname,uuid,grade) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        add(sql, user);

    }


    public List<User> resultFrom(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = (User) ResultTransformer.transform(User.class, rs);
            users.add(user);
        }
        return users;
    }

    private User extractUser(List<User> list) {
        if (list.size() == 0) return User.User_NULL;
        else return list.get(0);
    }
}

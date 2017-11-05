package com.xj.dao.impl;

import com.xj.dao.PictureDao;
import com.xj.domain.Picture;
import com.xj.exceptions.DaoException;
import com.xj.util.JdbcTransactionTemplate;
import com.xj.util.ResultTransformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public class PictureDaoImpl extends BaseDaoImpl<Picture> implements PictureDao {

    @Override
    public List<Picture> resultFrom(ResultSet rs) throws SQLException {
        List<Picture> pictures = new ArrayList<>();
        while (rs.next()) {
            Picture picture = (Picture) ResultTransformer.transform(Picture.class, rs);
            pictures.add(picture);
        }
        return pictures;
    }


    @Override
    public void addPicture(Picture picture) throws DaoException {
        String sql = "insert into picture(url) values (?)";
        add(sql, picture);
    }

    @Override
    public int addPicturePipe(Picture picture) throws Exception {
        ResultSet rs;
        String s2 = "INSERT INTO picture (url) VALUES(?)";
        JdbcTransactionTemplate.add(s2, picture);
        rs = JdbcTransactionTemplate.getPs().getGeneratedKeys();
        int picture_id = 0;
        while (rs.next()) {
            picture_id = rs.getInt(1);
        }
        return picture_id;
    }

    @Override
    public Picture queryById(int picture_id) throws DaoException {
        String sql="SELECT * FROM picture where id = ?";
        List<Picture> pictures=queryBySql(sql,picture_id);
        if(!pictures.isEmpty()) return pictures.get(0);
        return null;
    }


}

package com.xj.dao;

import com.xj.domain.Picture;
import com.xj.exceptions.DaoException;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public interface PictureDao extends BaseDao<Picture> {
    void addPicture(Picture picture) throws DaoException;

    int addPicturePipe(Picture picture) throws Exception;

    Picture queryById(int picture_id) throws DaoException;

}

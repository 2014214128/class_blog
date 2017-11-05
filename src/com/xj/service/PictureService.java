package com.xj.service;

import com.xj.domain.Picture;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public interface PictureService {
    void insert(Picture picture);

    Picture uploadPicture(HttpServletRequest req, FileItem item) throws Exception;

    Picture getById(int picture_id);

}

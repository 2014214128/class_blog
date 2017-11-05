package com.xj.service.impl;

import com.xj.dao.PictureDao;
import com.xj.dao.impl.PictureDaoImpl;
import com.xj.domain.Picture;
import com.xj.exceptions.DaoException;
import com.xj.service.PictureService;
import com.xj.util.LoggerTool;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public class PictureServiceImpl implements PictureService {
    private LoggerTool logger = LoggerTool.newInstance(PictureServiceImpl.class);

    @Override
    public void insert(Picture picture) {
        PictureDao dao = new PictureDaoImpl();
        try {
            dao.addPicture(picture);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
    }

    //上传图片，且图片在服务器中引用路径为"/blog/src/upload/........"
    @Override
    public Picture uploadPicture(HttpServletRequest req, FileItem item) throws Exception {
        //获得存放文件的物理路径
        String PATH = req.getServletContext().getRealPath("/") + File.separator + "src" + File.separator + "upload";
        //获取文件的url保存路径
        String fileName = parseFileName(item);
        if (fileName == null) return null;
        //获得文件名
        storePicture(item, PATH, fileName);
        Picture picture = new Picture();
        picture.setUrl(parseUrl(fileName));
        return picture;
    }

    private String parseUrl(String fileName) {
        return File.separator + "blog" + File.separator + "src" + File.separator + "upload" + File.separator+fileName;
    }

    private void storePicture(FileItem item, String PATH, String fileName) throws Exception {
        String storePath = PATH + File.separator + fileName;
        //存储UUID防止图片名重复进行覆盖
        File file = new File(storePath);
        if (!file.exists()) {
            item.write(file);
        }
    }

    private String parseFileName(FileItem item) {
        String old = item.getName();
        if (old == null || old.equals("")) return null;
        old = old.substring(old.lastIndexOf("/") + 1);
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString();
        String type = old.substring(old.indexOf("."));
        return sb.append(uuid).append(type).toString();
    }

    @Override
    public Picture getById(int picture_id) {
        PictureDao pictureDao = new PictureDaoImpl();
        Picture picture = new Picture();
        try {
            picture = pictureDao.queryById(picture_id);
        } catch (DaoException e) {
            logger.warn("查找照片出错" + e.getMessage());
        }
        return picture;
    }



    private String setStorePath(String path, String fileName) {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString();
        String type = fileName.substring(fileName.indexOf("."));
        sb.append(path).append(File.separator).append(uuid).append(type);
        return sb.toString();
    }

}

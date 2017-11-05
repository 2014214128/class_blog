package com.xj.service.impl;

import com.xj.dao.DraftDao;
import com.xj.dao.PictureDao;
import com.xj.dao.impl.DraftDaoImpl;
import com.xj.dao.impl.PictureDaoImpl;
import com.xj.domain.Draft;
import com.xj.domain.Picture;
import com.xj.dto.PageBean;
import com.xj.exceptions.DaoException;
import com.xj.service.DraftService;
import com.xj.util.JdbcTool;
import com.xj.util.JdbcTransactionTemplate;
import com.xj.util.LoggerTool;

import java.util.Date;
import java.util.List;

/**
 * Created by zhengguo on 2017/6/17.
 */
public class DraftServiceImpl implements DraftService{
    private DraftDao draftDao = new DraftDaoImpl();
    private PictureDao pictureDao = new PictureDaoImpl();
    @Override
    public PageBean<Draft> showDraftsByPage(PageBean<Draft> pageBean, int id) {
        pageBean.setData(draftDao.listDraftsByPage(id, pageBean.getPageNow(), pageBean.getPageSize()));
        pageBean.setRowCount(draftDao.getCounts(id));
        return pageBean;
    }

    @Override
    public void deleteDraft(int id) {
        draftDao.deleteDraft(id);
    }

    @Override
    public Draft findDraftById(int id) {
        return draftDao.findDraftById(id);
    }

    @Override
    public void save(Draft draft) {
        draftDao.addDraft(draft);
    }

    @Override
    public void save(Draft draft, Picture picture) {
        JdbcTool.beginTransaction();
        try {
            int picture_id = pictureDao.addPicturePipe(picture);
            draft.setTime(new Date());
            draft.setPicture_id(picture_id);
            draftDao.addDraft(draft);
            JdbcTool.endTransaction();
        } catch (Exception e) {
            JdbcTool.rollBackTrasaction();
        } finally {
            JdbcTool.closedPreparedStatement(JdbcTransactionTemplate.getPs());
            JdbcTool.closedConnection(JdbcTransactionTemplate.getConn());
        }
    }

    @Override
    public void update(Draft draft) {
        draftDao.updateDraft(draft);
    }

    @Override
    public void update(Draft draft, Picture picture) {
        JdbcTool.beginTransaction();
        try {
            int picture_id = pictureDao.addPicturePipe(picture);
            draft.setTime(new Date());
            draft.setPicture_id(picture_id);
            draftDao.updateDraft(draft);
            JdbcTool.endTransaction();
        } catch (Exception e) {
            JdbcTool.rollBackTrasaction();
        } finally {
            JdbcTool.closedPreparedStatement(JdbcTransactionTemplate.getPs());
            JdbcTool.closedConnection(JdbcTransactionTemplate.getConn());
        }
    }
}

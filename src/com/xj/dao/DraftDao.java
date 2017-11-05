package com.xj.dao;

import com.xj.domain.Draft;
import com.xj.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengguo on 2017/6/17.
 */
public interface DraftDao {
    void addDraft(Draft draft);

    void deleteDraft(int  id);

    Draft findDraftById(int id);

    void updateDraft(Draft draft);

    List<Draft> listDraftsByPage(int id, int pageNow, int pageSize);

    int getCounts(int id);
}

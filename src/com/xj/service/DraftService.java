package com.xj.service;

import com.xj.domain.Draft;
import com.xj.domain.Picture;
import com.xj.dto.PageBean;

/**
 * Created by zhengguo on 2017/6/17.
 */
public interface DraftService {

    PageBean<Draft> showDraftsByPage(PageBean<Draft> pageBean, int id);

    void deleteDraft(int id);

    Draft findDraftById(int id);

    void save(Draft draft);

    void save(Draft draft, Picture picture);

    void update(Draft draft);

    void update(Draft draft, Picture picture);
}

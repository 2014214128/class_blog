package com.xj.service;

import com.xj.dto.PageBean;

/**
 * Created by sheeran on 2017/6/22.
 * Talk is cheap show me code.
 */
public interface Pageable<T> {
    PageBean<T> listByPage(PageBean<T> pageBean, Object... objects);
}

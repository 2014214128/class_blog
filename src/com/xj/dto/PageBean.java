package com.xj.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheeran on 2017/6/4.
 * Talk is cheap show me code.
 */
public class PageBean<T> {
    private int pageNow;  //当前页
    private int pageSize; //每页显示数据数
    private int pageCount; //总页数
    private int rowCount;  //总数据量
    private List<T> data = new ArrayList<>();

    public PageBean(int pageNow, int pageSize) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        if (pageCount == 0)
            pageCount = (rowCount - 1) / pageSize + 1 ;
        return pageCount;
    }



    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

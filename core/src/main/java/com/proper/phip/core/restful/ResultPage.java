package com.proper.phip.core.restful;

import java.util.List;

/**
 * 返回分页记录类
 */
public class ResultPage<T> {

    private List<T> list;

    private Pagination pagination;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

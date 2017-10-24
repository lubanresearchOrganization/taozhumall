package com.lubanresearch.lubanmall.common.bean;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
public class Pagination<T> implements Serializable {
    private static final long serialVersionUID = 157102426434298982L;
    private List<T> items = Collections.emptyList();
    private long total;//总记录数
    private int size;//每页大小
    private int pageIndex;//当前页
    private int pageCount;//总页数

    public Pagination() {
        //提供以反序列化
    }

    public Pagination(List<T> items, long total, int size, int number) {
        this.items = items;
        this.total = total;
        this.size = size;
        this.pageIndex = number;
		this.pageCount = (int) (this.total - 1) / this.size + 1;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}

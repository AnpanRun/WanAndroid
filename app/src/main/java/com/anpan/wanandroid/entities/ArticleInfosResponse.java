package com.anpan.wanandroid.entities;

import java.util.ArrayList;

/**
 * 首页文章列表返回
 * Created by AnpanRun on 2021/1/31.
 */
public class ArticleInfosResponse {
    int curPage;
    ArrayList<ArticleInfo> datas;
    int offset;
    boolean over;
    int pageCount;
    int size;
    int total;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public ArrayList<ArticleInfo> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<ArticleInfo> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

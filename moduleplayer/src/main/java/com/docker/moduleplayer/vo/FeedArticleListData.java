package com.docker.moduleplayer.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.docker.commonlibrary.util.JsonUtil;

import java.util.List;

/**
 * @author https://www.jianshu.com/p/e2a7a45404cc 使用这种方法存库 room 没那么好用啊
 * @date 2018/2/12
 */
@Entity
public class FeedArticleListData {
    @PrimaryKey(autoGenerate = true)
    private int aid;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }


    private int curPage;

    @Ignore
    private List<FeedArticleData> datas;

    private String articleListStr;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public String getArticleListStr() {
        return articleListStr;
    }

    public void setArticleListStr(String articleListStr) {
        this.articleListStr = articleListStr;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<FeedArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<FeedArticleData> datas) {
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

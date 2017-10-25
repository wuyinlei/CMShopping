package com.cainiao.cainiao_ui.ui.refresh;

/**
 * Created by wuyinlei on 2017/10/25.
 */

public class PageBean {

    //当前是第几页
    private int mPageIndex = 0;

    //总数据条数
    private int mTotal = 0;

    //一页显示几条数据
    private int mPageSize = 0;

    //当前已经显示了几条数据
    private int mCurrentCount = 0;

    //加载延迟
    private int mDelayed = 0;


    public int getPageIndex() {
        return mPageIndex;
    }

    public PageBean setPageIndex(int pageIndex) {
        mPageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PageBean setTotal(int total) {
        mTotal = total;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PageBean setPageSize(int pageSize) {
        mPageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PageBean setCurrentCount(int currentCount) {
        mCurrentCount = currentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PageBean setDelayed(int delayed) {
        mDelayed = delayed;
        return this;
    }

    PageBean addIndex() {
        mPageIndex++;
        return this;
    }
}

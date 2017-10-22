package com.cainiao.cainiao_ui.ui.recycler;

import java.util.ArrayList;

/**
 * Created by wuyinlei on 2017/10/22.
 *
 * @function 数据转换约束
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!!");
        }
        return mJsonData;
    }

}

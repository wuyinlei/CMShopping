package com.ruolan.cainiao_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by wuyinlei on 2017/10/17.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {

    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}

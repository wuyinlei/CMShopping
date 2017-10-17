package com.ruolan.cainiao_ec.delegate.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruolan.cainiao_ec.database.DatabaseManager;
import com.ruolan.cainiao_ec.database.UserProfile;
import com.ruolan.cainiao_ec.database.UserProfileDao;

/**
 * Created by wuyinlei on 2017/10/17.
 *
 * @function 注册Handler
 */

public class SignHandler {

    /**
     * 数据持久化
     *
     * @param response 用户数据
     */
    public static void onSignIn(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

        DatabaseManager.getInstance().getDao().update(profile);
    }

    /**
     * 数据持久化
     *
     * @param response 用户数据
     */
    public static void onSignUp(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);

        UserProfileDao profileDao = DatabaseManager.getInstance().getDao();

        UserProfile userProfile = profileDao.load(userId);

        if (userProfile == null) {

            DatabaseManager.getInstance().getDao().insert(profile);

        }
    }
}

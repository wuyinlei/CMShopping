package com.ruolan.cainiao_ec.delegate.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.cainiao.cainiao_ui.ui.date.DateDialogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.net.callback.ISuccess;
import com.ruolan.cainiao_core.util.callback.CallbackManager;
import com.ruolan.cainiao_core.util.callback.CallbackType;
import com.ruolan.cainiao_core.util.callback.IGlobalCallback;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.delegate.main.personal.list.ListBean;
import com.ruolan.cainiao_ec.delegate.sign.SignHandler;

/**
 * Created by wuyinlei on 2017/11/8.
 *
 * @function
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback() {
                    @Override
                    public void executeCallback(Object args) {
                        uploadProfileAndUpdateInfo(view, (Uri) args);
                    }

                });

                //开始照相机或选择图片   修改头像
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //修改姓名


                break;
            case 3:
                //修改性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.dismiss();
                    }
                });

                break;
            case 4:
                //修改生日
                final DateDialogUtils dateDialogUtils = new DateDialogUtils();
                dateDialogUtils.setIDateListener(new DateDialogUtils.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView te = (TextView) view.findViewById(R.id.tv_arrow_value);
                        te.setText(date);
                    }
                });
                dateDialogUtils.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void uploadProfileAndUpdateInfo(View view, final Uri args) {

        final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
        Glide.with(DELEGATE)
                .load(args)
                .into(avatar);

        //这里既需要把剪切的头像显示在界面上  同时也是需要把图片上传到服务器上方便以后使用
        RestClient.builder()
                .url(UploadConfig.UPLOAD_PROFILT)
                .file(args.getPath())
                .loader(DELEGATE.getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        //解析出上传成功之后返回回来的头像地址
                        final String userAvatar =
                                JSON.parseObject(response)
                                        .getJSONObject("data")
                                        .getString("avarar");

                        //拿到上传到服务器的头像的地址  然后去通知服务器更新用户信息
                        RestClient.builder()
                                .url("59e57875f757730a12fd0752/test/user_profile")
                                .params("user_avatar", userAvatar)
                                .loader(DELEGATE.getContext())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        //更新用户信息
//                                        updataLocalUserInfo(response);
                                    }
                                })
                                .build()
                                .post();

                    }
                })
                .build()
                .upload();


    }

    /**
     * 用于更新本地用户信息
     *
     * @param userInfo 服务器返回的最新的用户信息
     */
    private void updataLocalUserInfo(String userInfo) {

        SignHandler.onSignIn(userInfo, null);

    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

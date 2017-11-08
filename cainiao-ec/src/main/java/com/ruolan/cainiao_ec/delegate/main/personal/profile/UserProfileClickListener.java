package com.ruolan.cainiao_ec.delegate.main.personal.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cainiao.cainiao_ui.ui.date.DateDialogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.ruolan.cainiao_core.net.RestClient;
import com.ruolan.cainiao_core.util.callback.CallbackManager;
import com.ruolan.cainiao_core.util.callback.CallbackType;
import com.ruolan.cainiao_core.util.callback.IGlobalCallback;
import com.ruolan.cainiao_core.util.log.CainiaoLogger;
import com.ruolan.cainiao_core.wechat.templete.AppRegisterTemplete;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.delegate.main.personal.list.ListBean;

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
//                        CainiaoLogger.d("ON_CROP", args);
                        uploadProfileAndUpdateInfo(view,args);
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

    private void uploadProfileAndUpdateInfo(View view, Object args) {

        final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
        Glide.with(DELEGATE)
                .load(args)
                .into(avatar);


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

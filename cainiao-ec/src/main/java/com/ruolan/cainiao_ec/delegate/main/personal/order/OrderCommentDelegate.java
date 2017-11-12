package com.ruolan.cainiao_ec.delegate.main.personal.order;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cainiao.cainiao_ui.ui.widget.AutoPhotoLayout;
import com.cainiao.cainiao_ui.ui.widget.StarLayout;
import com.ruolan.cainiao_core.delegate.CainiaoDelegate;
import com.ruolan.cainiao_core.util.callback.CallbackManager;
import com.ruolan.cainiao_core.util.callback.CallbackType;
import com.ruolan.cainiao_core.util.callback.IGlobalCallback;
import com.ruolan.cainiao_ec.R;
import com.ruolan.cainiao_ec.R2;
import com.ruolan.cainiao_ec.delegate.main.sort.content.ContentDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wuyinlei on 2017/11/6.
 *
 * @function
 */

@SuppressLint("ValidFragment")
public class OrderCommentDelegate extends CainiaoDelegate {


    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    private static final String ARG_CONTENT_ID = "PRODUCT_IMAGE_URL";


    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @BindView(R2.id.img_order_comment)
    ImageView mProductImg;

    private String mProductImage;


    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分： " + mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }

    public static OrderCommentDelegate newInstance(String contentId) {
        final Bundle args = new Bundle();
        args.putString(ARG_CONTENT_ID, contentId);
        final OrderCommentDelegate delegate = new OrderCommentDelegate();
        delegate.setArguments(args);
        Log.d("ContentDelegate", "contentId:" + contentId);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle != null) {
            mProductImage = bundle.getString(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        Glide.with(getProxyActivity())
                .load(mProductImage)
                .apply(OPTIONS)
                .into(mProductImg);

        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback() {
                    @Override
                    public void executeCallback(Object args) {

                    }
                });


    }
}

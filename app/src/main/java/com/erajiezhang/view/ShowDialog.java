package com.erajiezhang.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.erajiezhang.R;

/**
 * @author EraJieZhang
 * @data 2020/3/19
 */
public class ShowDialog extends Dialog {

    protected Context mContext;
    protected WindowManager.LayoutParams mLayoutParams;

    public ShowDialog(@NonNull Context context) {

        super(context);
        initView(context);
    }

    /**
     * 隐藏头部导航栏状态栏
     */
    public void skipTools() {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置全屏显示
     */
    public void setFullScreen() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        window.setAttributes(lp);
    }

    /**
     * 设置宽度match_parent
     */
    public void setFullScreenWidth() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    /**
     * 设置高度为match_parent
     */
    public void setFullScreenHeight() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        window.setAttributes(lp);
    }

    private void initView(Context context) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        mContext = context;
        Window window = this.getWindow();
        mLayoutParams = window.getAttributes();
        mLayoutParams.alpha = 1f;
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView(mContext);
        View dialogView = null;
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);
        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;

    }

//    private void initView(final Activity activity) {
//
//        View dialogView = null;
//        switch (mLayoutType) {
//            case TITLE:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);
//                break;
//            case NO_TITLE:
//                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_camero_show, null);
//                break;
//            default:
//                break;
//        }
//
//
//        mTvCamera = dialogView.findViewById(R.id.tv_camera);
//        mTvFile = dialogView.findViewById(R.id.tv_file);
//        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
//        mTvMsg = dialogView.findViewById(R.id.tv_msg);
//
//        if (! RxDataTool.isEmpty(mMsgText)) {
//            mTvMsg.setText(mMsgText);
//        }
//
//        if (! RxDataTool.isEmpty(mCameraText)) {
//            mTvCamera.setText(mCameraText);
//        }
//
//        if (! RxDataTool.isEmpty(mAlbumtText)) {
//            mTvFile.setText(mAlbumtText);
//        }
//
//
//        mTvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                cancel();
//            }
//        });
//        mTvCamera.setOnClickListener(mCameraOnClick);
//        mTvFile.setOnClickListener(mAlbumtOnClick);
//        setContentView(dialogView);
//        mLayoutParams.gravity = Gravity.BOTTOM;
//    }
}

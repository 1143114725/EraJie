package com.erajiezhang.view;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.erajiezhang.R;


/**
 * @author vondear
 * @date 2017/3/20
 * 封装了从相册/相机 获取 图片 的Dialog.
 */
public class RxDialogChooseImage extends RxDialog {




    private LayoutType mLayoutType = LayoutType.TITLE;
    private TextView mTvCamera;
    private TextView mTvFile;
    private TextView mTvCancel;
    private View mVLine;

    private View.OnClickListener mListener;

    public RxDialogChooseImage(Activity context) {

        super(context);
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment) {

        super(fragment.getContext());
        initView(fragment);
    }

    public RxDialogChooseImage(Activity context, int themeResId) {

        super(context, themeResId);
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, int themeResId) {

        super(fragment.getContext(), themeResId);
        initView(fragment);
    }

    public RxDialogChooseImage(Activity context, float alpha, int gravity) {

        super(context, alpha, gravity);
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, float alpha, int gravity) {

        super(fragment.getContext(), alpha, gravity);
        initView(fragment);
    }

    public RxDialogChooseImage(Fragment fragment, LayoutType layoutType) {

        super(fragment.getContext());
        mLayoutType = layoutType;
        initView(fragment);
    }

    public RxDialogChooseImage(Activity context, View.OnClickListener listener) {

        super(context);
        mListener = listener;
        initView(context);
    }

    public RxDialogChooseImage(Activity context, LayoutType layoutType) {

        super(context);
        mLayoutType = layoutType;
        initView(context);
    }

    public RxDialogChooseImage(Activity context, int themeResId, LayoutType layoutType) {

        super(context, themeResId);
        mLayoutType = layoutType;
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, int themeResId, LayoutType layoutType) {

        super(fragment.getContext(), themeResId);
        mLayoutType = layoutType;
        initView(fragment);
    }

    public RxDialogChooseImage(Activity context, float alpha, int gravity, LayoutType layoutType) {

        super(context, alpha, gravity);
        mLayoutType = layoutType;
        initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, float alpha, int gravity, LayoutType layoutType) {

        super(fragment.getContext(), alpha, gravity);
        mLayoutType = layoutType;
        initView(fragment);
    }

    public TextView getFromCameraView() {

        return mTvCamera;
    }

    public TextView getFromFileView() {

        return mTvFile;
    }

    public TextView getCancelView() {

        return mTvCancel;
    }

    public View getmVLine(){
        return mVLine;
    }


    public LayoutType getLayoutType() {

        return mLayoutType;
    }


    private void initView(final Activity activity) {

        View dialogView = null;
        switch (mLayoutType) {
            case TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);
                break;
            case NO_TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_camero_show, null);
                break;
            default:
                break;
        }


        mTvCamera = dialogView.findViewById(R.id.tv_camera);
        mTvFile = dialogView.findViewById(R.id.tv_file);
        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
        mVLine = dialogView.findViewById(R.id.v_line);



        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                cancel();
            }
        });
//        mTvCamera.setOnClickListener(mCameraOnClick);
//        mTvFile.setOnClickListener(mAlbumtOnClick);
        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    private void initView(final Fragment fragment) {

        View dialogView = null;
        switch (mLayoutType) {
            case TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);
                break;
            case NO_TITLE:
                dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_camero_show, null);
                break;
            default:
                break;
        }

        mTvCamera = dialogView.findViewById(R.id.tv_camera);
        mTvFile = dialogView.findViewById(R.id.tv_file);
        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
        mVLine = dialogView.findViewById(R.id.v_line);

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                cancel();
            }
        });
//        mTvCamera.setOnClickListener(mCameraOnClick);
//        mTvFile.setOnClickListener(mAlbumtOnClick);

        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    public enum LayoutType {
        TITLE, NO_TITLE
    }


}
package com.erajiezhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.erajie.arout.BaseArouteUtil;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;
import com.erajiezhang.bean.ReturnBean;
import com.erajiezhang.view.RxDialogChooseImage;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 * @author EraJieZhang
 * @data 2019/5/18
 */
@Route(path = ARouterPath.Mainacitvity)
public class Mainacitvity extends BaseActivity {

    @BindView(R.id.toarouter)
    Button mToarouter;
    @BindView(R.id.img_show)
    ImageView imgshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(mActivity);
        //		BaseArouteUtil.returnActivity(ARouterPath.HomeActivity,ARouterPath.GROUP_GENERAL);
    }

    @OnClick({R.id.toarouter, R.id.button2, R.id.button3, R.id.button7, R.id.button8})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.toarouter:
                RxLogTool.v("点击toarouter");
                RxDialogChooseImage dialogChooseImage =new RxDialogChooseImage(mActivity);


                if (!dialogChooseImage.isShowing()) {
                    dialogChooseImage.getFromCameraView().setText("保存图片");
                    dialogChooseImage.getFromFileView().setVisibility(View.GONE);
                    dialogChooseImage.getmVLine().setVisibility(View.GONE);
                    dialogChooseImage.getFromCameraView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RxLogTool.v("关闭弹窗");
                            dialogChooseImage.cancel();
                        }
                    });

                    dialogChooseImage.show();
                }
                break;
            case R.id.button2:
                BaseArouteUtil.returnActivity(ARouterPath.GameContentActivity, ARouterPath.GROUP_SCHULTEGRIL);
                break;
            case R.id.button3:
                BaseArouteUtil.returnActivity(ARouterPath.GobangActivity);
                break;
            case R.id.button7:
                BaseArouteUtil.getPostcard(ARouterPath.ThreelevellinkageActivity).
                        withString("rr", "jie").
                        withInt("z", 1).
                        withBoolean("b", true).
                        withParcelable("bean", (Parcelable) new ReturnBean("张世杰", "17090313417", "25")).
                        navigation(mActivity, 123);
                break;
            case R.id.button8:
                BaseArouteUtil.returnActivity(ARouterPath.ShowImageActivity);
                break;
            default:
                break;
        }
    }


    // 拦截的回调
    public class LoginNavigationCallbackImpl implements NavigationCallback {

        @Override
        public void onFound(Postcard postcard) {
            //找到了 onFound
            RxLogTool.v("找到了 onFound");
        }

        @Override
        public void onLost(Postcard postcard) {
            //找不到了 onLost
            RxLogTool.v("找不到了 onLost");
        }

        @Override
        public void onArrival(Postcard postcard) {
            //跳转成功了 onArrival
            RxLogTool.v("跳转成功了 onArrival");
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            //被拦截了 onInterrupt
            RxLogTool.v("被拦截了 onInterrupt");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 123:
                RxLogTool.v("接收到其他activity传过来的值");
                break;
        }
    }




}

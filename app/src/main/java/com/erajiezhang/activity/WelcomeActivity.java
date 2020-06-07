package com.erajiezhang.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.erajie.arout.BaseArouteUtil;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.global.PresetData;
import com.erajie.rxutils.RxDataTool;
import com.erajie.rxutils.RxDeviceTool;
import com.erajie.rxutils.SpUtil;
import com.erajiezhang.R;
import com.erajiezhang.db.DbManager;
import com.erajiezhang.db.UserDB;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.vondear.eeh.ui.CountDownTimerButton;

import java.util.List;

/**
 * @author EraJieZhang
 * @data 2020/3/29
 */

@Route(path = ARouterPath.APPWelcomeActivity,group = ARouterPath.GROUP_MAIN)
public class WelcomeActivity extends BaseActivity {
    private CountDownTimerButton mCountDownTimerButton;
    private ImageView mIvShow;
    private  String url = "http://cdn.duitang.com/uploads/item/201512/04/20151204212506_uxNWH.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int islogin = (int) com.erajiezhang.util.SpUtil.getParam(this,"islogin",0);
        if (islogin == 1){
            BaseArouteUtil.returnActivity(ARouterPath.Mainacitvity,ARouterPath.GROUP_MAIN);
            finish();
        }else{
            com.erajiezhang.util.SpUtil.setParam(this,"islogin",1);
        }

        QMUIStatusBarHelper.translucent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        List<UserDB> userDBList = DbManager.getDaoSession(mActivity).getUserDBDao().queryBuilder().build().list();
        /**
         * 如果没有账号
         */
        if (RxDataTool.isEmpty(userDBList)){

            UserDB userDB = new UserDB();
            userDB.setUsername("17090313417");
            userDB.setPassworld("143147");
            userDB.setDeviceId(RxDeviceTool.getIMEI(mActivity));
            userDB.setEnable("1");
            userDB.setPermissionID(0);
            userDB.setPhong("17090313417");
            userDB.setMailAddress("1143114725@qq.com");

            DbManager.getDaoSession(mActivity).getUserDBDao().save(userDB);
        }


        mCountDownTimerButton = findViewById(R.id.btn_welecome_count_time);
        mIvShow = findViewById(R.id.iv_welecome_show);
        //加载图片
        Glide.with(mActivity).load(url).into(mIvShow);
        mCountDownTimerButton.setClickAfter(" 秒");
        mCountDownTimerButton.setClickBeffor("进入");
        mCountDownTimerButton.setDuration(1);
        mCountDownTimerButton.startTimer();
        mCountDownTimerButton.setCallBack(new CountDownTimerButton.CallBack() {
            @Override
            public void onCleck() {
                if ((Boolean) SpUtil.getParam(mActivity,PresetData.IsLogin,false)){
                    BaseArouteUtil.returnActivity(ARouterPath.Mainacitvity,ARouterPath.GROUP_MAIN);
//                    BaseArouteUtil.returnActivity(ARouterPath.GobangActivity,ARouterPath.GROUP_MAIN);
                }else{
                    BaseArouteUtil.returnActivity(ARouterPath.LoginActivity,ARouterPath.GROUP_MAIN);
//                    BaseArouteUtil.returnActivity(ARouterPath.GobangActivity,ARouterPath.GROUP_MAIN);
                }
                mActivity.finish();
            }
        });

    }
}

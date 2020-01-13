package com.eeh.schultegril.activity;

import android.Manifest;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.eeh.schultegril.R;
import com.eeh.schultegril.base.BaseActivity;
import com.erajie.rxutils.PermissionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页activity
 */
public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";
    @BindView(R.id.start_gamne_welcome)
    Button mStartGamneWelcome;
    @BindView(R.id.aboout_us_welcome)
    Button mAbooutUsWelcome;

    private long exitTime = 0;


    @Override
    protected void initView() {

        setContentView(R.layout.layout_activity_welcome);
        ButterKnife.bind(this);
    }

    @Override
    protected void initLastPageDate() {

    }

    @Override
    protected void initData() {


    }

    /**
     * 获取全部权限
     */
    private void getermission() {

        String permission[] = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
        PermissionUtil.isPermissions(mActivity, 200, permission, new PermissionUtil.Operation() {
            @Override
            public void OnNext() {

//                BaseToast.showShortToast(mActivity, "权限注册成功！");
            }
        });
    }

    /**
     * 退出按钮监控
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                BaseToast.showShortToast(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
//                AppManager.getAppManager().AppExit();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.start_gamne_welcome, R.id.aboout_us_welcome})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.start_gamne_welcome:
                Log.i(TAG, "onViewClicked: 开始游戏");
                goToActivity(mActivity,GameContentActivity.class);

                break;
            case R.id.aboout_us_welcome:
                Log.i(TAG, "onViewClicked: 关于我们");

                goToActivity(mActivity, ContactUsActivity.class);

                break;
            default:
                break;
        }
    }

}


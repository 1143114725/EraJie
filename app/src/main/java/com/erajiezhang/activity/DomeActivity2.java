package com.erajiezhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;
import com.erajiezhang.util.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author EraJieZhang
 * @data 2019/1/2
 */
public class DomeActivity2 extends BaseActivity {
@BindView(R.id.btn1_dome)
Button mBtn1Dome;
@BindView(R.id.bt2_dome)
Button mBt2Dome;
@BindView(R.id.btn3_dome)
Button mBtn3Dome;
@BindView(R.id.btn4_dome)
Button mBtn4Dome;
@BindView(R.id.tv_dome_title)
TextView mTvDomeTitle;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_dome);
    ButterKnife.bind(this);
    mTvDomeTitle.setText(R.string.string_title2);
}

@OnClick({R.id.btn1_dome, R.id.bt2_dome, R.id.btn3_dome, R.id.btn4_dome})
public void onViewClicked(View view) {
    switch (view.getId()) {
        case R.id.btn1_dome:
            AppManager.getAppManager().ShowAllActivity();
            break;
        case R.id.bt2_dome:
//            DomeActivity
            goToActivity(mActivity,DomeActivity3.class);
            break;
        case R.id.btn3_dome:
            finish();
            break;
        case R.id.btn4_dome:
            AppManager.getAppManager().AppExit();
            break;
        default:
            break;
    }
}
}

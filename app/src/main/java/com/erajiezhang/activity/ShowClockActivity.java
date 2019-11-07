package com.erajiezhang.activity;

import android.os.Bundle;

import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author EraJieZhang
 * @data 2019/11/7
 */
public class ShowClockActivity extends BaseActivity {
	
	
	@BindView(R.id.clockview)
	com.erajiezhang.view.TextClockView mClockView;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showclock_layout);
		ButterKnife.bind(mActivity);
		
		mClockView.start(true);
//		Timer mTimer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						Log.e("MainActivity.java(run)", "行数: 32  走");
//						mClockView.doInvalidate();
//					}
//				});
//
//			}
//		};
//		mTimer.schedule(task, 1000, 1000);
	}

}

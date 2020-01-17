package com.eeh.schultegril.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.eeh.schultegril.R;
import com.eeh.schultegril.R2;
import com.eeh.schultegril.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxAppTool;
import com.erajie.rxutils.RxClipboardTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 联系我们
 *
 * @author EraJieZhang
 * @data 2018年11月8日
 */
@Route(path = ARouterPath.ContactUsActivity,group = ARouterPath.GROUP_SCHULTEGRIL)
public class ContactUsActivity extends BaseActivity {
	
	private static final String TAG = "ContactUsActivity";
	@BindView(R2.id.tv_bar_back)
	ImageView mTvBarBack;
	@BindView(R2.id.tv_bar_title)
	TextView mTvBarTitle;
	@BindView(R2.id.tv_bar_nemu)
	ImageView mTvBarNemu;
	@BindView(R2.id.tv_contactus_QQ)
	TextView mTvContactusQQ;
	@BindView(R2.id.tv_contactus_Email)
	TextView mTvContactusEmail;
	@BindView(R2.id.tv_contactus_Version)
	TextView mTvContactusVersion;
	
	
	@Override
	protected void initView() {
		
		setContentView(R.layout.layout_activity_contactus);
		ButterKnife.bind(this);
		mTvBarNemu.setVisibility(View.INVISIBLE);
		mTvBarTitle.setText("关于我们");
		String version = RxAppTool.getAppVersionName(mActivity);
		
		mTvContactusVersion.setText(String.format(version, R.string.app_version));
	}
	
	@Override
	protected void initLastPageDate() {
	
	
	}
	
	@Override
	protected void initData() {
		
		String qq = getString(R.string.QQ);
		mTvContactusQQ.setText(String.format("QQ:", R.string.QQ));
		mTvContactusEmail.setText(String.format("邮箱:", R.string.Email));
	}
	
	
	@OnClick({R2.id.tv_bar_back, R2.id.tv_contactus_QQ, R2.id.tv_contactus_Email})
	public void onViewClicked(View view) {
		
		int id = view.getId();
		if (id ==  R.id.tv_bar_back){
			finish();
		}else if (id == R.id.tv_contactus_QQ){
			RxClipboardTool.copyText(mActivity, "613691251");
		}else if (id == R.id.tv_contactus_Email){
			RxClipboardTool.copyText(mActivity, "erajiezhang@foxmail.com");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		butterknife.ButterKnife.bind(this);
	}
}

package com.erajiezhang.activity;

import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.global.ARouterPath;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;
import com.erajiezhang.view.GobangView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author EraJieZhang
 * @data 2020/2/10
 */
@Route(path = ARouterPath.GobangActivity)
public class GobangActivity extends BaseActivity {
	@BindView(R.id.gobangview)
	GobangView mGobangview;
	@BindView(R.id.btn_statr)
	Button mBtnStatr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gobank_layout);
		ButterKnife.bind(this);
		
	}
	
	@OnClick(R.id.btn_statr)
	public void onViewClicked() {
		mGobangview.regretgame();
	}
}

package com.erajiezhang.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.R;
import com.erajiezhang.bean.ReturnBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试罗盘时钟
 * @author EraJieZhang
 * @data 2019/11/7
 */
@Route(path = ARouterPath.ShowClockActivity)
public class ShowClockActivity extends BaseActivity {
	
	@Autowired(name = "rr")
	public String era;
	@Autowired(name = "z")
	public int z;
	
	@Autowired(name = "b")
	public boolean mBoolean;
	
	@Autowired(name = "bean")
	ReturnBean mReturnBean;
	
	@BindView(R.id.clockview)
	com.erajiezhang.view.TextClockView mClockView;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showclock_layout);
		ButterKnife.bind(mActivity);
		ARouter.getInstance().inject(this);
		
		RxLogTool.v("era = " + era );
		RxLogTool.v("z = " + z );
		RxLogTool.v("mBoolean = " + mBoolean );
//		RxLogTool.v("mReturnBean = " + mReturnBean.toString() );
		
		mClockView.start(true);
	}

}

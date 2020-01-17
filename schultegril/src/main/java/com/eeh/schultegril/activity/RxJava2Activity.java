package com.eeh.schultegril.activity;

import android.widget.Button;
import android.widget.TextView;

import com.eeh.schultegril.R;
import com.eeh.schultegril.R2;
import com.eeh.schultegril.base.BaseActivity;
import com.erajie.SendHttpRequest;
import com.erajie.httptoos.HttpCallBack;
import com.erajie.responsebean.ToolLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试Rxjava
 * Created by EEH on 2018/10/23.
 */
public class RxJava2Activity extends BaseActivity {
	
	private static final String TAG = "RxJava2Activity";
	@BindView(R2.id.rxjava2btn)
	Button mRxjava2btn;
	@BindView(R2.id.rxjava2tv)
	TextView mRxjava2tv;
	
	/**
	 * 唯一单例模式
	 *
	 * @return
	 */
	private static RxJava2Activity mInstance;
	
	public synchronized static RxJava2Activity getInstance() {
		
		if ( mInstance == null ) {
			mInstance = new RxJava2Activity();
		}
		return mInstance;
	}
	
	@Override
	protected void initView() {
		
		setContentView(R.layout.rxjava2_layout);
		ButterKnife.bind(this);
	}
	
	@Override
	protected void initLastPageDate() {
	
	}
	
	@Override
	protected void initData() {
	
	}
	
	@OnClick(R2.id.rxjava2btn)
	public void onViewClicked() {
		
		SendHttpRequest.sendLogin(mActivity, new HttpCallBack<ToolLogin>() {
			@Override
			public void onNext(ToolLogin toolLogin) {
				
				String text = toolLogin.toString();
//                BaseLog.v(TAG, "请求成功：" + text);
				mRxjava2tv.setText(toolLogin.getName());
			}
			
			@Override
			public void onError(String msg) {

//                BaseLog.v(TAG, "请求成功：" + msg);
			
			}
		});
		
		
	}
	
}

package cn.eeh.general.advertising;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxDataTool;
import com.erajie.rxutils.RxLogTool;
import com.erajie.rxutils.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.eeh.general.R;
import cn.eeh.general.R2;

/**
 * 通用网页广告WebView
 *
 * @author EraJieZhang
 * @data 2020/2/19
 *  url 网页的url
 *  type 标识类别（备用）
 *  state 状态（备用）
 */
@Route(path = ARouterPath.AdvertisingActivity,group = ARouterPath.GROUP_GENERAL)
public class AdvertisingActivity extends BaseActivity {
	@BindView(R2.id.ll_ad_back)
	LinearLayout mLlAdBack;
	@BindView(R2.id.tv_ad_title)
	TextView mTvAdTitle;
	@BindView(R2.id.tv_ad_right)
	TextView mTvAdRight;
	@BindView(R2.id.webview)
	WebView mWebview;
	@Autowired(name = "url")
	String url;
	@Autowired(name = "type")
	int type;
	@Autowired(name = "state")
	int state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_advertising);
		ButterKnife.bind(this);
		ARouter.getInstance().inject(this);
		
		RxLogTool.v("url = " + url);
		RxLogTool.v("type = " + type);
		if ( !RxDataTool.isEmpty(state) ){
			RxLogTool.v("state = " + state);
		}
		
		initData();
	}
	
	private void initData() {
		
		RxLogTool.v("AdvertisingActivity url = " + url);
		mWebview.loadUrl(url);
		
		/* 加载标题 */
		mWebview.setWebChromeClient(new WebChromeClient() {
			
			@Override
			public void onReceivedTitle(WebView view, String title) {
				mTvAdTitle.setText(title);
			}
		});
		//防止跳转到浏览器
		mWebview.setWebViewClient(new WebViewClient());
		
		WebSettings webSettings = mWebview.getSettings();
		/* 设置支持javascript脚本 */
		webSettings.setJavaScriptEnabled(true);
		/* 设置可以支持缩放 */
		webSettings.setSupportZoom(true);
		/* 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false */
		webSettings.setBuiltInZoomControls(true);
		/* 隐藏缩放工具 */
		webSettings.setDisplayZoomControls(false);
		/* 扩大比例的缩放 */
		webSettings.setUseWideViewPort(true);
		/* 自适应屏幕 */
		webSettings
			.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webSettings.setLoadWithOverviewMode(true);
		
		webSettings.setDatabaseEnabled(true);
		/*
		 * 是否开启本地DOM存储 鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android
		 * 默认是关闭该功能的。
		 */
		webSettings.setDomStorageEnabled(true);
		mWebview.setSaveEnabled(true);
		mWebview.setKeepScreenOn(true);
		
	}
	
	@Override
	public boolean
	onKeyDown(int keyCode, KeyEvent event) {
		if ( keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack() ) {
			mWebview.goBack();//返回上个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);//退出H5界面
	}
	
	@OnClick({R2.id.ll_ad_back, R2.id.tv_ad_title, R2.id.tv_ad_right})
	public void onViewClicked(View view) {
		int id = view.getId();
		if(id == R.id.ll_ad_back){
			RxLogTool.v("tv-ad-back-----");
			mActivity.finish();
		}else
		if(id == R.id.tv_ad_title){
			RxLogTool.v("tv-ad-title-----");
		}else
		if(id == R.id.tv_ad_right){
		RxLogTool.v("tv-ad-rigth-----");
			RxToast.success("分享成功！");
		}
		
	
	}
}

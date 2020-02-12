package cn.eeh.general.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.arout.BaseArouteUtil;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.eeh.general.R;
import cn.eeh.general.R2;

/**
 * 引导页面
 * @author EraJieZhang
 * @data 2020/2/18
 */
@Route(path = ARouterPath.HomeActivity, group = ARouterPath.GROUP_GENERAL)
public class HomeActivity extends BaseActivity {
	
	@BindView(R2.id.button)
	Button mButton;
	@BindView(R2.id.con_adview)
	ConstraintLayout mConAdview;
	@BindView(R2.id.ll_msg)
	LinearLayout mLlMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		ButterKnife.bind(this);
	}
	
	
	@OnClick({R2.id.button, R2.id.con_adview})
	public void onViewClicked(View view) {
		int id = view.getId();
		if (id == R.id.button ){
			
			BaseArouteUtil.returnActivity(ARouterPath.MainActivity,ARouterPath.GROUP_GENERAL);
		
		}else if ( id == R.id.con_adview){
			String url = "https://newdrugs.dxy.cn/h5/temp/disease_topics?s=dxyappqdy0203&sf=1&dn=2";
			ARouter.getInstance().build(ARouterPath.AdvertisingActivity)
				.withString("url",url)
				.withInt("type",1)
				.navigation();
		}
		
		
	}
}

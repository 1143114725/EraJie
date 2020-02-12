package cn.eeh.general.main;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.AppManager;
import com.erajie.global.ARouterPath;
import com.erajie.global.PresetData;
import com.erajie.rxutils.GsonUtil;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import cn.eeh.general.R;
import cn.eeh.general.bean.MinuBean;
import cn.eeh.general.databinding.LayoutMainBinding;

/**
 * 主页activity
 *
 * @author EraJieZhang
 * @data 2020/2/19
 */
@Route(path = ARouterPath.MainActivity, group = ARouterPath.GROUP_GENERAL)
public class MainActivity extends FragmentActivity  {
	private MinuBean mMinuBean;
	private LayoutMainBinding mainBinding;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainBinding = DataBindingUtil.setContentView(this, R.layout.layout_main);
		mMinuBean = GsonUtil.GsonToBean(PresetData.mMinuBean,MinuBean.class);
		mainBinding.setOnClick(new OnClickHandler(mMinuBean,this));
		mainBinding.setMinuBean(mMinuBean);
	}
	
	//返回两次退出程序
	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN ) {
			if ( (System.currentTimeMillis() - exitTime) > 2000 ) {
				Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
				toast.setText("再按一次退出程序");
				toast.show();
				exitTime = System.currentTimeMillis();
			} else {
				AppManager.getAppManager().AppExit();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}

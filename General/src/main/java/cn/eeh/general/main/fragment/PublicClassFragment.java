package cn.eeh.general.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.BaseFragment;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import cn.eeh.general.R;
import cn.eeh.general.databinding.FragmentMyBinding;

/**
 * 公开课页面
 * @author EraJieZhang
 * @data 2020/2/20
 */
@Route(path = ARouterPath.PublicClassFragment,group = ARouterPath.GROUP_GENERAL)
public class PublicClassFragment extends BaseFragment {
	FragmentMyBinding mBinding;
	
	/**
	 * 唯一单例模式
	 *
	 * @return
	 */
	private static PublicClassFragment mInstance;
	
	public synchronized static PublicClassFragment getInstance() {
		if ( mInstance == null ) {
			mInstance = new PublicClassFragment();
		}
		return mInstance;
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false);
		View view = mBinding.getRoot();
		TextView textView = view.findViewById(R.id.tv_fragment_content);
		textView.setText("公开课页面");
		return view;
	}
	
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RxLogTool.d("PublicClassFragment ---->>> onCreate");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		RxLogTool.d("PublicClassFragment ---->>> onResume");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		RxLogTool.d("PublicClassFragment ---->>> onStop");
	}
	
	
}

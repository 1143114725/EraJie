package cn.eeh.general.main.fragment.academic;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.BaseFragment;
import com.erajie.global.ARouterPath;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import cn.eeh.general.R;
import cn.eeh.general.databinding.FragmentAcademicBinding;
import cn.eeh.general.main.fragment.FoundFragment;
import cn.eeh.general.main.fragment.MyFragment;
import cn.eeh.general.main.fragment.PublicClassFragment;

/**
 * 学术圈页面
 * @author EraJieZhang
 * @data 2020/2/20
 */
@Route(path = ARouterPath.AcademicCirclesFragment,group = ARouterPath.GROUP_GENERAL)
public class AcademicCirclesFragment extends BaseFragment{
	private FragmentAcademicBinding mBinding;
	
	private ViewPager2 mViewPager;
	/**
	 * 唯一单例模式
	 *
	 * @return
	 */
	private static AcademicCirclesFragment mInstance;
	
	public synchronized static AcademicCirclesFragment getInstance() {
		if ( mInstance == null ) {
			mInstance = new AcademicCirclesFragment();
		}
		return mInstance;
	}
	private List<Integer> list = new ArrayList<Integer>();
	private AcademicAdapter mAcademicAdapter;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_academic, container, false);
		View view = mBinding.getRoot();
		ViewPager2 mViewPager = view.findViewById(R.id.vp_academin_main);
		ArrayList<Fragment> fragments = new ArrayList<>();
		
		fragments.add(FoundFragment.getInstance());
		fragments.add(PublicClassFragment.getInstance());
		fragments.add(MyFragment.getInstance());
		
		
		if (list != null) list.clear();
		list.add(Color.RED);
		list.add(Color.GRAY);
		list.add(Color.BLUE);
		list.add(Color.YELLOW);
		mAcademicAdapter = new AcademicAdapter(mActivity, list);
		mViewPager.setAdapter(mAcademicAdapter);
		
		return view;
	}
	
	
	
}
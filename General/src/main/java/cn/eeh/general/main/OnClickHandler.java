package cn.eeh.general.main;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import cn.eeh.general.R;
import cn.eeh.general.bean.MinuBean;
import cn.eeh.general.main.fragment.FoundFragment;
import cn.eeh.general.main.fragment.MyFragment;
import cn.eeh.general.main.fragment.PublicClassFragment;
import cn.eeh.general.main.fragment.academic.AcademicCirclesFragment;

/**
 * @author EraJieZhang
 * @data 2020/2/20
 */
public class OnClickHandler {
	public OnClickHandler() {
	}
	MinuBean mMinuBean = new MinuBean();
	FragmentActivity mFragmentActivity;
	AcademicCirclesFragment academicCirclesFragment =  (AcademicCirclesFragment)ARouter.getInstance().build(ARouterPath.AcademicCirclesFragment).navigation();
	PublicClassFragment mPublicClassFragment = (PublicClassFragment) ARouter.getInstance().build(ARouterPath.PublicClassFragment).navigation();
	FoundFragment mFoundFragment = (FoundFragment) ARouter.getInstance().build(ARouterPath.FoundFragment).navigation();
	MyFragment myFragment = (MyFragment) ARouter.getInstance().build(ARouterPath.MyFragment).navigation();
	
	
	public OnClickHandler(MinuBean minuBean, FragmentActivity context) {
		mMinuBean = minuBean;
		mFragmentActivity = context;
		getFragmentTransaction().add(R.id.fl_main_root,academicCirclesFragment)
			.add(R.id.fl_main_root, mPublicClassFragment)
			.add(R.id.fl_main_root,mFoundFragment)
			.add(R.id.fl_main_root,myFragment).commit();
		
		changeFragment(1);
	}
	
	/**
	 * 获取fragment管理工具
	 * @return
	 */
	@NotNull
	private FragmentTransaction getFragmentTransaction(){
		return mFragmentActivity.getSupportFragmentManager().beginTransaction();
	}
	
	
	public void onClickAcademicCircles(View view){
		RxLogTool.v("点击事件--onClickAcademicCircles");
//		getFragmentTransaction().setMaxLifecycle(academicCirclesFragment, Lifecycle.State.STARTED).commit();
		changeFragment(1);
	}
	public void onClickPublicClass(View view){
		RxLogTool.v("点击事件--onClickPublicClass");
//
//		mFragmentTransaction.add(R.id.fl_main_root,mPublicClassFragment);
//		hideFragment();
//		getFragmentTransaction().setMaxLifecycle(mPublicClassFragment, Lifecycle.State.STARTED).commit();
		changeFragment(2);

	}
	public void onClickFound(View view){
		RxLogTool.v("点击事件--onClickFound");
		
//		mFragmentTransaction.add(R.id.fl_main_root,mFoundFragment);
//		getFragmentTransaction().setMaxLifecycle(mFoundFragment, Lifecycle.State.STARTED).commit();
		changeFragment(3);
	}
	public void onClickMy(View view){
		RxLogTool.v("点击事件--onClickMy");
		
//		mFragmentTransaction.add(R.id.fl_main_root,myFragment);
//		getFragmentTransaction().setMaxLifecycle(myFragment, Lifecycle.State.STARTED).commit();
		changeFragment(4);
	}
	
	

	 /**
	 *  隐藏fragment
	 */
	private void hideFragment() {
		FragmentTransaction transaction = getFragmentTransaction();
		if (null != academicCirclesFragment) {
			transaction.hide(academicCirclesFragment);
			transaction.setMaxLifecycle(academicCirclesFragment,Lifecycle.State.STARTED);
		}
		if (null != mPublicClassFragment) {
			transaction.hide(mPublicClassFragment);
			transaction.setMaxLifecycle(mPublicClassFragment,Lifecycle.State.STARTED);
		}
		if (null != mFoundFragment) {
			transaction.hide(mFoundFragment);
			transaction.setMaxLifecycle(mFoundFragment,Lifecycle.State.STARTED);
		}
		if (null != myFragment) {
			transaction.hide(myFragment);
			transaction.setMaxLifecycle(myFragment,Lifecycle.State.STARTED);
		}
		transaction.commit();
	}
	
	/**
	 * 切换fragment
	 * @param index
	 */
	public void changeFragment(int index) {
		FragmentTransaction ft = getFragmentTransaction();
		hideFragment();
		
		switch (index) {
			case 1:
				if (null == academicCirclesFragment) {
					academicCirclesFragment = AcademicCirclesFragment.getInstance();
					ft.add(R.id.fl_main_root,academicCirclesFragment);
				} else {
					ft.show(academicCirclesFragment);
				}
				ft.setMaxLifecycle(academicCirclesFragment, Lifecycle.State.STARTED);
				break;
			case 2:
				if (null == mPublicClassFragment) {
					mPublicClassFragment = PublicClassFragment.getInstance();
					ft.add(R.id.fl_main_root, mPublicClassFragment);
				} else {
					ft.show(mPublicClassFragment);
				}
				ft.setMaxLifecycle(mPublicClassFragment, Lifecycle.State.STARTED);
				break;
			case 3:
				if (null == mFoundFragment) {
					mFoundFragment = FoundFragment.getInstance();
					ft.add(R.id.fl_main_root, mFoundFragment);
				} else {
					ft.show(mFoundFragment);
				}
				ft.setMaxLifecycle(mFoundFragment, Lifecycle.State.STARTED);
				break;

			case 4:
				if (null == myFragment) {
					myFragment = MyFragment.getInstance();
					ft.add(R.id.fl_main_root, myFragment);
				} else {
					ft.show(myFragment);
				}
				ft.setMaxLifecycle(myFragment, Lifecycle.State.STARTED);
				break;
			default:
				break;
		}
		ft.commit();
	}
	
	
	
	
	
}

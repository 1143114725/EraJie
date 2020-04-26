package com.erajiezhang.powersurvey;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.global.PresetData;
import com.erajie.rxutils.GsonUtil;
import com.erajiezhang.R;
import com.erajiezhang.powersurvey.fragment.OrdinaryFragment;
import com.erajiezhang.powersurvey.fragment.UserFragment;
import com.erajiezhang.viewmodle.BarViewModleBean;
import com.erajiezhang.viewmodle.PowerSurveyBean;

import java.util.ArrayList;

/**
 * @author EraJieZhang
 * @data 2020/4/19
 */
@Route(path = ARouterPath.PowerSurveyMainActivity,group = ARouterPath.GROUP_MAIN)
public class PowerSurveyMainActivity extends BaseActivity {

    ViewDataBinding mpowersurveyBinding;
    PowerSurveyAdapter powerSurveyAdapter;
    private ViewPager2 mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mpowersurveyBinding = DataBindingUtil.setContentView(mActivity, R.layout.powersurvey_main);
        BarViewModleBean barViewModleBean = GsonUtil.GsonToBean(PresetData.mBarModle, BarViewModleBean.class);
        PowerSurveyBean powerSurveyBean = new PowerSurveyBean(mActivity);
        powerSurveyBean.setOrdinary(getResources().getString(R.string.string_powerSurvey_ordinary));
        powerSurveyBean.setUser(getResources().getString(R.string.string_powerSurvey_user));

        mpowersurveyBinding.setVariable(com.erajiezhang.BR.powerbean, powerSurveyBean);
        mpowersurveyBinding.setVariable(com.erajiezhang.BR.barModle, barViewModleBean);

        mViewPager = findViewById(R.id.vp_powersurvey_root);


        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(OrdinaryFragment.getInstance());
        fragments.add(UserFragment.getInstance());

        powerSurveyAdapter = new PowerSurveyAdapter(this,fragments);
        mViewPager.setAdapter(powerSurveyAdapter);
        powerSurveyBean.setmViewPager(mViewPager);


    }
}

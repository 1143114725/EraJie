package com.erajiezhang.powersurvey;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.erajie.base.BaseActivity;
import com.erajie.global.PresetData;
import com.erajie.rxutils.GsonUtil;
import com.erajiezhang.R;
import com.erajiezhang.viewmodle.BarViewModleBean;
import com.erajiezhang.viewmodle.PowerSurveyBean;

/**
 * @author EraJieZhang
 * @data 2020/4/19
 */
public class PowerSurveyMainActivity extends BaseActivity {

    ViewDataBinding mpowersurveyBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mpowersurveyBinding = DataBindingUtil.setContentView(mActivity, R.layout.powersurvey_main);
        BarViewModleBean barViewModleBean = GsonUtil.GsonToBean(PresetData.mBarModle,BarViewModleBean.class);
        PowerSurveyBean powerSurveyBean = new PowerSurveyBean();
        powerSurveyBean.setOrdinary("123");
        powerSurveyBean.setUser("456");
        mpowersurveyBinding.setVariable(com.erajiezhang.BR.loginModle,powerSurveyBean);
        mpowersurveyBinding.setVariable(com.erajiezhang.BR.barModle,barViewModleBean);

    }
}

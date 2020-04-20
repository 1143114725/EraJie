package com.erajiezhang.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.global.PresetData;
import com.erajie.rxutils.GsonUtil;
import com.erajiezhang.R;
import com.erajiezhang.databinding.ActivityLoginBinding;
import com.erajiezhang.presenter.LoginPresenter;
import com.erajiezhang.viewmodle.BarViewModleBean;
import com.erajiezhang.viewmodle.LoginViewModleBean;

/**
 * @author EraJieZhang
 * @data 2020/3/29
 */
/**这个是设置tint(图标着色器)的映射方法 写在哪里都行好像*/
@BindingMethods({@BindingMethod(type = ImageView.class, attribute = "tint", method = "setImageTintList")})
@Route(path = ARouterPath.LoginActivity, group = ARouterPath.GROUP_MAIN)
public class LoginActivity extends BaseActivity {

    ActivityLoginBinding mloginBinding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mloginBinding = DataBindingUtil.setContentView(mActivity, R.layout.activity_login);
        LoginViewModleBean loginViewModleBean = GsonUtil.GsonToBean(PresetData.mLoginViewModleBean,LoginViewModleBean.class);
        BarViewModleBean barViewModleBean = GsonUtil.GsonToBean(PresetData.mBarModle,BarViewModleBean.class);
        mloginBinding.setVariable(com.erajiezhang.BR.loginModle,loginViewModleBean);
        mloginBinding.setVariable(com.erajiezhang.BR.barModle,barViewModleBean);
        mloginBinding.setLoginOnClick(new LoginPresenter(mActivity,loginViewModleBean));
    }
}

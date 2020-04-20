package com.erajiezhang.viewmodle;

import android.view.View;

import androidx.databinding.Bindable;

import com.erajie.base.BaseViewModle;
import com.erajiezhang.BR;

/**
 * 电力调查首页
 * @author EraJieZhang
 * @data 2020/4/20
 */
public class PowerSurveyBean extends BaseViewModle {

    private String ordinary;
    private String user;
    @Bindable
    public String getOrdinary() {

        return ordinary;
    }
    @Bindable
    public String getUser() {

        return user;
    }

    public void setOrdinary(String ordinary) {

        this.ordinary = ordinary;
        notifyPropertyChanged(BR.ordinary);
    }

    public void setUser(String user) {

        this.user = user;
        notifyPropertyChanged(BR.user);
    }



    public void onClickOrdinary(View view){

    }

    public void onClickUser(View view){

    }
}

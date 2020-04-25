package com.erajiezhang.viewmodle;

import android.app.Activity;
import android.view.View;

import androidx.databinding.Bindable;
import androidx.viewpager2.widget.ViewPager2;

import com.erajie.base.BaseViewModle;
import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.BR;

/**
 * 电力调查首页
 * @author EraJieZhang
 * @data 2020/4/20
 */
public class PowerSurveyBean extends BaseViewModle {

    public PowerSurveyBean(Activity mActivity) {

        this.mActivity = mActivity;
    }

    private Activity mActivity;
    private String ordinary;
    private String user;
    private ViewPager2 mViewPager;
    private ViewPager2.OnPageChangeCallback callback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {

            super.onPageSelected(position);
            switch (position){
                case 0:
                    clickOrdinary();
                break;
                case 1:
                    clickUser();
                break;
                default:
                    break;
            }
        }
    };

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

    public ViewPager2 getmViewPager() {

        return mViewPager;
    }

    public void setmViewPager(ViewPager2 mViewPager) {

        this.mViewPager = mViewPager;
        this.mViewPager.registerOnPageChangeCallback(callback);

    }

    public void onClickOrdinary(View view){
        clickOrdinary();

    }

    private void clickOrdinary(){

        RxLogTool.v("点击：" + ordinary);
        mViewPager.setCurrentItem(0);


    }

    public void onClickUser(View view){
        clickUser();
    }

    private void clickUser(){
        RxLogTool.v("点击：" + user);
        mViewPager.setCurrentItem(1);
    }
}

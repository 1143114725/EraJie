package com.erajiezhang.powersurvey;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @author EraJieZhang
 * @data 2020/2/27
 */
public class PowerSurveyAdapter extends FragmentStateAdapter {


    Context context;
    List<Fragment> list;

    public PowerSurveyAdapter(@NonNull FragmentActivity fragmentActivity,List<Fragment> list) {

        super(fragmentActivity);
        this.list = list;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return list.get(position);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    //    public PowerSurveyAdapter(@NonNull FragmentManager fm, int behavior, Context context, List<Fragment> list) {
//
//        super(fm, behavior);
//        this.context = context;
//        this.list = list;
//
//    }


}
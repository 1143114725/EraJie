package com.erajiezhang.powersurvey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.erajie.base.BaseFragment;
import com.erajiezhang.R;

import cn.eeh.general.databinding.FragmentMyBinding;

/**
 * @author EraJieZhang
 * @data 2020/4/20
 */
public class UserFragment extends BaseFragment {

    /**
     * 唯一单例模式
     * @return
     */
    private static UserFragment mInstance;

    public synchronized static UserFragment getInstance() {

        if (mInstance == null) {
            mInstance = new UserFragment();
        }
        return mInstance;
    }

    FragmentMyBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false);
        View view = mBinding.getRoot();
        TextView textView = view.findViewById(R.id.tv_fragment_content);
        textView.setText("发现页面");
        return view;
    }
}

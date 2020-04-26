package com.erajiezhang.powersurvey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.erajie.base.BaseFragment;
import com.erajiezhang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

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

    @BindView(R.id.videoplayer)
    JzvdStd mJzvdStd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.user_fragment, null);
        ButterKnife.bind(this, root);


        mJzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4",
                "饺子快长大",
                1);
        Glide.with(this)
                .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(mJzvdStd.posterImageView);

        return root;
    }



}

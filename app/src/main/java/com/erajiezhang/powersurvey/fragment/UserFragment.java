package com.erajiezhang.powersurvey.fragment;

import com.erajie.base.BaseFragment;

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
}

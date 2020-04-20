package com.erajiezhang.powersurvey.fragment;

import com.erajie.base.BaseFragment;

/**
 * @author EraJieZhang
 * @data 2020/4/20
 */
public class OrdinaryFragment extends BaseFragment {

    /**
     * 唯一单例模式
     * @return
     */
    private static OrdinaryFragment mInstance;

    public synchronized static OrdinaryFragment getInstance() {

        if (mInstance == null) {
            mInstance = new OrdinaryFragment();
        }
        return mInstance;
    }
}

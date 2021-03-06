package com.erajiezhang.powersurvey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erajie.base.BaseFragment;
import com.erajiezhang.R;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @BindView(R.id.emptyView)
    QMUIEmptyView mEmptyView;
    @BindView(R.id.rv_ordinary)
    RecyclerView mRvOrdinary;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    OrdinaryAdapter mOrdinaryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = LayoutInflater.from(getActivity()).inflate(R.layout.ordinary_fragment, null);
        ButterKnife.bind(this, root);

        setRecycleViewDate();

        setEmptyView();

        setRefreshLinstener();


        return root;
    }

    /**
     * 设置错误页面
     */
    private void setEmptyView() {
        mEmptyView.show(true);

        mEmptyView.postDelayed(new Runnable() {
            @Override
            public void run() {

                mEmptyView.show(false, "加载失败", null, "点击重试", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mEmptyView.setVisibility(View.GONE);

                    }
                });
            }
        }, 1000);
    }

    /**
     * 设置刷新事件
     */
    private void setRefreshLinstener() {


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(/*2000,0.
                 */false);//传入false表示刷新失败
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    /**
     * 设置recycleView的数据绑定
     */
    private void setRecycleViewDate() {
        /*假数据*/
        List<OrdinaryBean> list = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            OrdinaryBean ordinaryBean= new OrdinaryBean();
            ordinaryBean.id = i+1;
            ordinaryBean.name = "EraJieZhang";
            ordinaryBean.time = "2020年4月24日17:53:33";
            list.add(ordinaryBean);
        }

        mRvOrdinary.setLayoutManager(new LinearLayoutManager(mActivity));
        mOrdinaryAdapter = new OrdinaryAdapter(mActivity, list);
        mRvOrdinary.setAdapter(mOrdinaryAdapter);
        //添加Android自带的分割线
        mRvOrdinary.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }
}

package com.erajiezhang.powersurvey.fragment;

import android.content.Context;

import com.erajie.base.BaseRecyclerAdapter;
import com.erajie.base.RecyclerViewHolder;
import com.erajiezhang.R;

import java.util.List;

/**
 * @author EraJieZhang
 * @data 2020/4/24
 */
public class OrdinaryAdapter extends BaseRecyclerAdapter<OrdinaryBean> {


    public OrdinaryAdapter(Context ctx, List<OrdinaryBean> list) {

        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {

        return R.layout.item_ordinary;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, OrdinaryBean item) {

        holder.getTextView(R.id.tv_ordinary_item_id).setText("ID:" + item.getId());
        holder.getTextView(R.id.tv_ordinary_item_name).setText(item.getName());
        holder.getTextView(R.id.tv_ordinary_item_time).setText(item.getTime());

    }
}

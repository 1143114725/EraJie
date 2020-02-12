package com.erajie.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Mvvm recycleView adapter公用继承
 * 未完成：点击事件
 * @author EraJieZhang
 * @data 2020/2/20
 */
public class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewAdapter.CommonViewHolder> {
	Context context;
	LayoutInflater layoutInflater;
	int layoutId;
	int variableId;
	List<T> lists;
	public CommonRecyclerViewAdapter(Context context, LayoutInflater layoutInflater, int layoutId, int variableId, List<T> lists) {
		this.context = context;
		this.layoutInflater = layoutInflater;
		this.layoutId = layoutId;
		this.variableId = variableId;
		this.lists = lists;
	}
	@Override
	public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		//先得到viewdatabinding对象，这个对象中会持有条目item 布局的引用
		ViewDataBinding viewDataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
		//***切记，一定要使用viewdatabinding中的view的引用去实例化viewholder对象，否则将不会有任何效果
		CommonViewHolder commonViewHolder = new CommonViewHolder(viewDataBinding.getRoot().getRootView());
		//为下文中的onBindViewHolder方法进行铺垫，即让viewholder与viewdatabinding捆绑在一起（注意setBinding是我们自己提供的方法）
		commonViewHolder.setBinding(viewDataBinding);
		return commonViewHolder;
	}
	@Override
	public void onBindViewHolder(CommonRecyclerViewAdapter.CommonViewHolder holder, int position) {
		//更新视图
		holder.setContent(lists.get(position));
	}
	@Override
	public int getItemCount() {
		return lists.size();
	}
	public class CommonViewHolder extends RecyclerView.ViewHolder {
		//以前是定义一堆控件，现在呢?定义一下viewdatabinding ,用它去设置变量
		ViewDataBinding viewDataBinding;
		public CommonViewHolder(View itemView) {
			super(itemView);
		}
		public void setBinding(ViewDataBinding binding) {
			viewDataBinding = binding;
		}
		public ViewDataBinding getBinding() {
			return viewDataBinding;
		}
		public void setContent(T t) {
			viewDataBinding.setVariable(variableId, t);
//          必须加上，这会强制绑定操作马上执行，而不是推迟到下一帧刷新时，
//          RecyclerView 会在 onBindViewHolder 之后立即测量 View。
//          如果因为绑定推迟到下一帧绘制时导致错误的数据被绑定到 View 中, View 会被不正确地测量，
			viewDataBinding.executePendingBindings();
		}
	}
}
package com.erajie.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 自定义的BaseAdapter，所有adapter的父类。
 * @author EraJieZhang
 * @since 2015年12月14日
 */
public abstract class MyBaseAdapter<T, Q> extends BaseAdapter {

	public Context context;
	public List<T> list;
	public Q view;

	 @Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	public MyBaseAdapter(Context context, List<T> list, Q view) {
		this.context = context;
		this.list = list;
		this.view = view;
	}

	public MyBaseAdapter(Context context, List<T> list) {
		this.context = context;
		this.list = list;

	}

	/**
	 * update
	 *
	 * @param list
	 */
	public void updateListView(List<T> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	
	/**
	 * 决定了listview一共有多少个item
	 */
	
	@Override
	public int getCount() {
		return list.size();
	}
	
	/**
	 * 获得相应数据集合中特定位置的数据项
	 * @param position
	 * @return
	 */
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	
	/**
	 * 它返回的是该postion对应item的id,一般 item的id就时他的position
	 * @param position
	 * @return
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

}

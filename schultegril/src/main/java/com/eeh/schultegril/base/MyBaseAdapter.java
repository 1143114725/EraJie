package com.eeh.schultegril.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author EraJieZhang
 * @Description:自定义的BaseAdapter，所有adapter的父类。
 * @since 2015年12月14日
 */
public abstract class MyBaseAdapter<T, Q> extends BaseAdapter {
	
	public Context context;
	public List<T> list;//
	public Q view; // 这里不一定是ListView,比如GridView
	
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
	
	//决定了listview一共有多少个item
	@Override
	public int getCount() {
		return list.size();
	}
	
	//官方解释是Get the data item associated with the specified position in the data set.
	// 即获得相应数据集合中特定位置的数据项
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
	
	//它返回的是该postion对应item的id
	//一般 item的id就时他的position
	@Override
	public long getItemId(int position) {
		return position;
	}
	
}

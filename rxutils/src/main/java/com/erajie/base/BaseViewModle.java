package com.erajie.base;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

/**
 * Mvvm 数据Bean继承类
 * @author EraJieZhang
 * @data 2020/2/20
 */
public class BaseViewModle extends BaseObservable {
	@BindingAdapter({"android:src"})
	public static void setImageViewResource(ImageView imageView, int resource){
		imageView.setImageResource(resource);
	}
	
	/**
	 * 刷新所有数据
	 */
	public  void notifyChangeAll(){
		notifyChange();
	}
	
}

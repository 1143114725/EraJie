package com.erajie.base;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Mvvm 数据Bean继承类
 * @author EraJieZhang
 * @data 2020/2/20
 *
 * 使用方法--
 * @Bindable
 * get（）
 *
 * set(){
 *     notifyPropertyChanged(cn.eeh.general.BR.academicCircles);
 * }
 */
public class BaseViewModle extends BaseObservable {
	@BindingAdapter({"app:src"})
	public static void setImageViewResource(ImageView imageView, Object object){

		Glide.with(imageView.getContext()).load(object).into(imageView);
	}

	@BindingAdapter({"app:psdinputType"})
	public static void setInputType(EditText editText, int inputType){
		editText.setInputType(inputType);
	}

	/**
	 * 刷新所有数据
	 */
	public  void notifyChangeAll(){
		notifyChange();
	}
	
}

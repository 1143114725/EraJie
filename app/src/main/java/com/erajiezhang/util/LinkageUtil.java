package com.erajiezhang.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.erajiezhang.R;

import java.util.ArrayList;

/**
 * @author EraJieZhang
 * @data 2019/5/18
 */
public class LinkageUtil {
	
	/**
	 * 给spinner设置数据
	 * @param context
	 * @param lsitdate
	 * @param sp
	 */
	public static void setSpinnerdate(Context context, ArrayList<String> lsitdate, Spinner sp) {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
			R.layout.sp_item_dome,
			lsitdate);
		sp.setAdapter(adapter);
	}
}

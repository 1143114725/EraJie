package com.erajiezhang.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;
import com.erajiezhang.util.LinkageUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试三级联动
 *
 * @author EraJieZhang
 * @data 2019/5/18
 */
public class Mainacitvity extends BaseActivity {
	/**
	 * 省
	 */
	@BindView(R.id.sp_dome_province)
	Spinner mSpDomeProvince;
	ArrayList<String> provincelist = new ArrayList<>();
	/**
	 * 市
	 */
	@BindView(R.id.sp_dome_caty)
	Spinner mSpDomeCaty;
	ArrayList<String> catylist = new ArrayList<>();
	/**
	 * 区
	 */
	@BindView(R.id.sp_dome_district)
	Spinner mSpDomeDistrict;
	ArrayList<String> districtlist = new ArrayList<>();
	@BindView(R.id.tv_dome_title)
		
	TextView mTvDomeTitle;
	
//	private HtmlSpanner htmlSpanner = new HtmlSpanner();
	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dome);
		ButterKnife.bind(mActivity);
		
		setdate();
		String text = "<span style=font-size:36px;>sssss</span>";
		SpannableString spannableString = new SpannableString(text) ;
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.RED);
		spannableString.setSpan(backgroundColorSpan, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		mTvDomeTitle.setText(spannableString) ;
	
//		Spanned spanned = Html.fromHtml(text,0);
//
//		mTvDomeTitle.setText(spanned);
	}
	
	
	private void setdate() {
		
		provincelist.add("A:beijing");
		provincelist.add("A:shanghai");
		catylist.add("B:shanghai");
		catylist.add("B:shanghai");
		districtlist.add("C:shanghai");
		districtlist.add("C:shanghai");
		
		
		LinkageUtil.setSpinnerdate(mActivity, provincelist, mSpDomeProvince);
		LinkageUtil.setSpinnerdate(mActivity, catylist, mSpDomeCaty);
		LinkageUtil.setSpinnerdate(mActivity, districtlist, mSpDomeDistrict);
		
		
		mSpDomeProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				RxLogTool.v("A:position=" + position);
				RxLogTool.v("A:id=" + id);
				TextView mTvspitemdome = parent.findViewById(R.id.tv_spitem_dome);
				String text = mTvspitemdome.getText().toString().trim();
				RxLogTool.v("A:text=" + text);
				
				if ( id == 1 ) {
					LinkageUtil.setSpinnerdate(mActivity, districtlist, mSpDomeCaty);
				}
				if ( id == 0 ) {
					LinkageUtil.setSpinnerdate(mActivity, catylist, mSpDomeCaty);
				}
				
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		
		mSpDomeCaty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				RxLogTool.v("B:position=" + position);
				RxLogTool.v("B:id=" + id);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
	}
	
	
}

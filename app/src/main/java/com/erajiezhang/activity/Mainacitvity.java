package com.erajiezhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.erajie.rxutils.RxLogTool;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dome);
		ButterKnife.bind(mActivity);
		
		setdate();
	}
	
	
	private void setdate() {
		
		provincelist.add("A:beijing");
		provincelist.add("A:shanghai");
		catylist.add("B:shanghai");
		catylist.add("B:shanghai");
		districtlist.add("C:shanghai");
		districtlist.add("C:shanghai");
		
		
		
		
		setSpinnerdate(provincelist,mSpDomeProvince);
		setSpinnerdate(catylist,mSpDomeCaty);
		setSpinnerdate(districtlist,mSpDomeDistrict);
		
		mSpDomeProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				RxLogTool.v("A:position=" + position);
				RxLogTool.v("A:id=" + id);
				TextView mTvspitemdome = parent.findViewById(R.id.tv_spitem_dome);
				String  text = mTvspitemdome.getText().toString().trim();
				RxLogTool.v("A:text=" + text);
				
				if ( id ==1 ){
					setSpinnerdate(districtlist,mSpDomeCaty);
				}
				if ( id ==0){
					setSpinnerdate(catylist,mSpDomeCaty);
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
//				TextView mTvspitemdome = parent.findViewById(R.id.tv_spitem_dome);
//				String  text = mTvspitemdome.getText().toString().trim();
//				RxLogTool.v("B:text=" + text);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
	}
	
	private void setSpinnerdate(ArrayList<String> lsitdate,Spinner sp) {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity,
			R.layout.sp_item_dome,
			lsitdate);
		sp.setAdapter(adapter);
	}
	
	
}

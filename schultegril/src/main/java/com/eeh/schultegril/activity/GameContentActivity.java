package com.eeh.schultegril.activity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eeh.schultegril.R;
import com.eeh.schultegril.R2;
import com.eeh.schultegril.base.BaseActivity;
import com.erajie.global.ARouterPath;
import com.erajie.rxutils.RxLogTool;
import com.erajie.rxutils.view.RxToast;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 游戏主界面
 * Created by EEH on 2018/9/17.
 * @author EraJi
 */
@Route(path = ARouterPath.GameContentActivity,group = ARouterPath.GROUP_SCHULTEGRIL)
public class GameContentActivity extends BaseActivity {
	
	private static final String TAG = "GameContentActivity";
	
	ArrayList<String> anslist = new ArrayList<>();
	ArrayList<String> shulist = new ArrayList<>();
	int index = 0;
	@BindView(R2.id.ll_root)
	LinearLayout mLlRoot;
	int dp8;
	int dp16;
	@BindView(R2.id.tv_gamecontent_nextclick)
	TextView mTvGamecontentNextclick;
	@BindView(R2.id.tv_bar_back)
	ImageView mTvBarBack;
	@BindView(R2.id.tv_bar_title)
	TextView mTvBarTitle;
	@BindView(R2.id.tv_bar_nemu)
	ImageView mTvBarNemu;
	
	
	@Autowired(name = "rr")
	public String era;
	@Autowired(name = "z")
	public int z;
	
	@Autowired(name = "b")
	public boolean mBoolean;
	
	
	@Override
	protected void initView() {
		
		setContentView(R.layout.layout_activity_gamecontent);
		ButterKnife.bind(this);
		ARouter.getInstance().inject(this);
		
		RxLogTool.v("era = " + era );
		RxLogTool.v("z = " + z );
		RxLogTool.v("mBoolean = " + mBoolean );
		
		
		mTvBarTitle.setText("请开始你的表演");
		mTvBarNemu.setVisibility(View.GONE);
		dp8 = (int) getResources().getDimension(R.dimen.dp_8);
		dp16 = (int) getResources().getDimension(R.dimen.dp_16);
	}
	
	@Override
	protected void initLastPageDate() {
		
		for (int i = 0; i < 25; i++) {
			anslist.add("" + (i + 1));
			shulist.add("" + (i + 1));
		}
		
		
		Collections.shuffle(shulist);
	}
	
	@Override
	protected void initData() {
		
		mTvGamecontentNextclick.setText("下一个：" + anslist.get(0));
		
		
		for (int i = 0; i < 5; i++) {
			LinearLayout lltb = createLinearLayout();
			for (int j = 0; j < 5; j++) {
				lltb.addView(createTextView(shulist.get(j + i * 5)));
			}
			mLlRoot.addView(lltb);
		}
	}
	
	/**
	 * 创建ll
	 *
	 * @return
	 */
	private LinearLayout createLinearLayout() {
		//新建linearlayout
		LinearLayout linearLayout = new LinearLayout(mActivity);
		/*linearLayout.setBackgroundColor(getResources().getColor(R.color.white));*/
		linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.setMargins(dp8, dp8, dp8, dp8);
		linearLayout.setLayoutParams(params);
		return linearLayout;
	}
	
	/**
	 * 创建tv 设置tag
	 *
	 * @param tag
	 * @return
	 */
	private TextView createTextView(final String tag) {
		
		final TextView textView = new TextView(mActivity);
		textView.setBackgroundResource(R.drawable.bg_gamebtn);
		
		textView.setPadding(dp16, dp16, dp16, dp16);
		textView.setText(tag);
		textView.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams textviewparams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		textviewparams.setMargins(dp8, dp8, dp8, dp8);
		textviewparams.weight = 1;
		textView.setLayoutParams(textviewparams);
		
		textView.setTag(tag);
		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String gettag = (String) textView.getTag();
				if ( gettag.equals(anslist.get(index)) ) {
                      /*修改背景颜色
                      textView.setBackgroundResource(R.color.colorAccent);*/
					
					index++;
					if ( anslist.size() == index ) {
						RxToast.info("游戏结束！");
						setnexttext("没有了");
						index = 0;
					} else {
						RxToast.info("恭喜回答正确！" + tag);
						setnexttext(anslist.get(index));
					}
				}
			}
		});
		return textView;
	}
	
	/**
	 * 修改提示
	 *
	 * @param num
	 */
	private void setnexttext(String num) {
		
		String nexttext = "下一个：" + num;
		mTvGamecontentNextclick.setText(nexttext);
	}
	
	
	@OnClick({R2.id.tv_bar_back, R2.id.tv_bar_nemu})
	public void onViewClicked(View view) {
		int id = view.getId();
		if (id ==  R.id.tv_bar_back){
			finish();
		}else if (id == R.id.tv_bar_nemu){
			RxToast.success("menu");
		}

	}
}
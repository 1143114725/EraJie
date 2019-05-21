package com.erajiezhang.activity;

import android.graphics.Color;
import android.view.View;

import com.bumptech.glide.util.Util;
import com.erajiezhang.R;

/**
 * @author EraJieZhang
 * @data 2019/5/21
 */
public class New {
	
						if (realRows % 2 == 0) {
		ll.setBackgroundColor(Color.parseColor("#F0F0F0"));
	} else {
		ll.setBackgroundColor(Color.TRANSPARENT);
	}
						if (0 == c) {
		/**
		 * 打印行标题
		 */
		tvTb.setBackgroundResource(R.drawable.small_text_background);
		tvTb.setTextColor(Color.BLACK);
		tvTb.setPadding(1, 1, 1, 1);
		// 矩阵右侧
		float imgMaxWidth = maxCWidth / 3;
		tvTb.setWidth(maxCWidth / 3);
		if (1 == q.isRight) {
			tvTb.setWidth(maxCWidth / 6);
			imgMaxWidth = maxCWidth / 6;
		}
		
		// tvTb.setBackgroundColor(Color.TRANSPARENT);
		String t = rowItem.itemText;
		
		/**
		 * 题外关联 之 选项置顶 单选矩阵 的提示语的 显示 出来
		 */
		if (null != rowItem.padding) {
			if (rowItem.padding == 1) {
				t = rowItem.itemText
					+ "<font color=red>  "
					+ this.getString(R.string.option_top)
					+ "</font>";
			} else if (rowItem.padding == 2) {
				t = rowItem.itemText
					+ "<font color=red>  "
					+ this.getString(R.string.option_bottom)
					+ "</font>";
			}
		}
		// ***********************************样式处理**************************//
		
		// if (!Util.isEmpty(t)) {
		// CstmMatcher cm = Util.findFontMatcherList(t);
		// if (!Util.isEmpty(cm.getMis())) {
		// t = cm.getResultStr();
		// if (!Util.isEmpty(t)) {
		// SpannableString ss = new SpannableString(t);
		// for (MatcherItem mi : cm.getMis()) {
		// ss.setSpan(new ForegroundColorSpan(mi.color),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// CstmMatcher bCm = Util.findBoldMatcherList(t);
		// if (!Util.isEmpty(bCm.getMis())) {
		// for (MatcherItem mi : bCm.getMis()) {
		// if (null != mi && -1 != mi.start && -1 != mi.end
		// && mi.end <= t.length()) {
		// ss.setSpan(new StyleSpan(Typeface.BOLD),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// ss.setSpan(new RelativeSizeSpan(1.3f), mi.start,
		// mi.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// }
		// }
		// tvTb.setText(ss);
		// } else {
		// tvTb.setText(rowItem.itemText);
		// }
		// //
		// } else {
		// CstmMatcher bCm = Util.findBoldMatcherList(t);
		// if (!Util.isEmpty(bCm.getMis())) {
		// t = bCm.getResultStr();
		// if (!Util.isEmpty(t)) {
		// SpannableString ss = new SpannableString(t);
		// for (MatcherItem mi : bCm.getMis()) {
		// if (null != mi && -1 != mi.start && -1 != mi.end
		// && mi.end <= t.length()) {
		// ss.setSpan(new StyleSpan(Typeface.BOLD),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// ss.setSpan(new RelativeSizeSpan(1.3f), mi.start,
		// mi.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// }
		// tvTb.setText(ss);
		// } else {
		// tvTb.setText(rowItem.itemText);
		// }
		// } else {
		// tvTb.setText(t);
		// }
		//
		// }
		//
		// }
		// 样式修改 以上注释
		// ***********************************样式处理**************************//
		if (!Util.isEmpty(t)) {
			final float Width = (imgMaxWidth - 5);
			// 更改的样式
			ImageGetter imgGetter = new Html.ImageGetter() {
				public Drawable getDrawable(String source) {
					Drawable drawable = null;
					String name = NativeModeActivity.this
						.getFilesDir()
						+ File.separator
						+ "survey"
						+ File.separator
						+ feed.getSurveyId()
						+ File.separator + source;
					// System.out.println("name:" + name);
					drawable = Drawable
						.createFromPath(name);
					Bitmap image = BitmapFactory
						.decodeFile(name);
					if (image != null) {
						float tWidth = image.getWidth();
						float tHeight = image.getHeight();
						if (tWidth > Width) {
							tHeight = Width / tWidth
								* tHeight;
							tWidth = Width;
						}
						drawable.setBounds(0, 0,
							(int) tWidth, (int) tHeight);
						return drawable;
					} else {
						return null;
					}
				}
			};
			t = t.replace("style=font-size:", "size=");
			Spanned fromHtml = Html.fromHtml(t, imgGetter,
				new HtmlTagHandler());
			tvTb.setText(fromHtml);
		}
		ll.addView(tvTb, ll.getChildCount());
		// ***********************************样式处理**************************//
		// if (1 == q.qStarCheck) {
		// TextView tvLine = new
		// TextView(NativeModeActivity.this);
		// tvLine.setBackgroundColor(Color.LTGRAY);
		// tvLine.setLayoutParams(new LayoutParams(3,
		// LayoutParams.MATCH_PARENT));
		// ll.addView(tvLine);
		// }
	}
	// 矩阵右侧 新加的
						else if (rColmns.size() + rightNum == c
								&& q.isRight == 1) {
		/**
		 * 打印行标题
		 */
		tvTb.setBackgroundResource(R.drawable.small_text_background);
		tvTb.setTextColor(Color.BLACK);
		// 矩阵右侧
		float imgMaxWidth = maxCWidth / 6;
		tvTb.setWidth(maxCWidth / 6);
		tvTb.setGravity(Gravity.FILL);
		tvTb.setPadding(2, 2, 2, 2);
		// tvTb.setBackgroundColor(Color.TRANSPARENT);
		String t = rowItem.itemTextRight;
		// ***********************************样式处理**************************//
		
		// if (!Util.isEmpty(t)) {
		// CstmMatcher cm = Util.findFontMatcherList(t);
		// if (!Util.isEmpty(cm.getMis())) {
		// t = cm.getResultStr();
		// if (!Util.isEmpty(t)) {
		// SpannableString ss = new SpannableString(t);
		// for (MatcherItem mi : cm.getMis()) {
		// ss.setSpan(new ForegroundColorSpan(mi.color),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// CstmMatcher bCm = Util.findBoldMatcherList(t);
		// if (!Util.isEmpty(bCm.getMis())) {
		// for (MatcherItem mi : bCm.getMis()) {
		// if (null != mi && -1 != mi.start && -1 != mi.end
		// && mi.end <= t.length()) {
		// ss.setSpan(new StyleSpan(Typeface.BOLD),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// ss.setSpan(new RelativeSizeSpan(1.3f), mi.start,
		// mi.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// }
		// }
		// tvTb.setText(ss);
		// } else {
		// tvTb.setText(rowItem.itemText);
		// }
		// //
		// } else {
		// CstmMatcher bCm = Util.findBoldMatcherList(t);
		// if (!Util.isEmpty(bCm.getMis())) {
		// t = bCm.getResultStr();
		// if (!Util.isEmpty(t)) {
		// SpannableString ss = new SpannableString(t);
		// for (MatcherItem mi : bCm.getMis()) {
		// if (null != mi && -1 != mi.start && -1 != mi.end
		// && mi.end <= t.length()) {
		// ss.setSpan(new StyleSpan(Typeface.BOLD),
		// mi.start, mi.end,
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// ss.setSpan(new RelativeSizeSpan(1.3f), mi.start,
		// mi.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// }
		// tvTb.setText(ss);
		// } else {
		// tvTb.setText(rowItem.itemText);
		// }
		// } else {
		// tvTb.setText(t);
		// }
		//
		// }
		//
		// }
		// 样式修改 以上注释
		// ***********************************样式处理**************************//
		if (!Util.isEmpty(t)) {
			final float Width = imgMaxWidth - 5;
			// 更改的样式
			ImageGetter imgGetter = new Html.ImageGetter() {
				public Drawable getDrawable(String source) {
					Drawable drawable = null;
					String name = NativeModeActivity.this
						.getFilesDir()
						+ File.separator
						+ "survey"
						+ File.separator
						+ feed.getSurveyId()
						+ File.separator + source;
					// System.out.println("name:" + name);
					drawable = Drawable
						.createFromPath(name);
					Bitmap image = BitmapFactory
						.decodeFile(name);
					if (image != null) {
						float tWidth = image.getWidth();
						float tHeight = image.getHeight();
						if (tWidth > Width) {
							tHeight = Width / tWidth
								* tHeight;
							tWidth = Width;
						}
						drawable.setBounds(0, 0,
							(int) tWidth, (int) tHeight);
						return drawable;
					} else {
						return null;
					}
				}
			};
			t = t.replace("style=font-size:", "size=");
			Spanned fromHtml = Html.fromHtml(t, imgGetter,
				new HtmlTagHandler());
			tvTb.setText(fromHtml);
		}
		ll.addView(tvTb, ll.getChildCount());
	} else {// 打印单选按钮
		if (1 == c) {
			ll.addView(rg);
		}
		// QuestionItem item = rRows.get(r - 1);
		if (null == rowItem
			|| (1 == rowItem.isOther && -1 == rowItem.itemValue)) {
			// 空其他项,即只有一个<freeInput/>标签
			continue;
		}
		// System.out.println("r_row_item_value=" +
		// rowItem.itemValue + ", r=" + (r - 1));
		RadioButton radio = new RadioButton(
			NativeModeActivity.this);
		
		radio.setLayoutParams(WRAP_WRAP);
		radio.setGravity(Gravity.FILL
			| Gravity.CENTER_VERTICAL);
		// radio.setGravity(Gravity.CENTER);
		
		AnswerMap am = new AnswerMap();
		String name = Util.GetAnswerName(q, rowItem,
			rowItem.itemValue, 0, false, false);
		am.setAnswerName(name);
		/**
		 * 相对的
		 */
		am.setRow(rowItem.itemValue);
		/**
		 * 绝对的
		 */
		am.setCol(colItem.itemValue);
		// radio.setOnClickListener(new On(rowItem,
		// colItem));
		// System.out.println("单选矩阵随机--->Key(" +
		// rowItem.itemText + "," + colItem.itemText +
		// ")====>Value(" + rowItem.itemValue + ", " +
		// colItem.itemValue + ")");
		am.setAnswerValue(String.valueOf(colItem.itemValue));
		radio.setTag(am);
		// radio.setTag(key, tag)
		rg.addView(radio, rg.getChildCount());
		if (!Util.isEmpty(amList)) {
			for (AnswerMap tam : amList) {
				if (name.equals(tam.getAnswerName().trim())
					&& am.getAnswerValue()
					.trim()
					.equals(tam
						.getAnswerValue()
						.trim())) {
					// System.out.println("匹配--->name="+name+",
					// value="+am.getAnswerValue());
					radio.setChecked(true);
				}
			}
		} else {// 预选项
			if (1 == colItem.deft) {
				if (Util.isEmpty(amList)) {
					radio.setChecked(true);
				}
			}
		}
		
		radio.setTextSize(TypedValue.COMPLEX_UNIT_PX,
			lowSurveySize);
		radio.setTextColor(Color.BLACK);
		// radio.setButtonDrawable(R.drawable.small_radiobutton_temp);
		radio.setButtonDrawable(R.drawable.small_radiobutton);
		radio.setBackgroundResource(R.drawable.small_radiobutton_background);
		if (q.qStarCheck != 0) {
			int drawable = R.drawable.small_radiobutton;
			radio.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(drawable),
				null, null, null);
			radio.setButtonDrawable(android.R.color.transparent);
			radio.setBackgroundResource(R.drawable.small_text_background);
			if (realRows % 2 == 0) {
				radio.setBackgroundColor(Color.WHITE);
			} else {
				radio.setBackgroundColor(Color
					.parseColor("#F0F0F0"));
			}
			switch (q.qStarCheck) {
				case 1:
					drawable = android.R.color.transparent;
					TextView tvLine = new TextView(
						NativeModeActivity.this);
					if (realRows % 2 == 0) {
						tvLine.setBackgroundColor(Color
							.parseColor("#F0F0F0"));
						radio.setBackgroundColor(Color.WHITE);
					} else {
						tvLine.setBackgroundColor(Color.WHITE);
						radio.setBackgroundColor(Color
							.parseColor("#F0F0F0"));
					}
					tvLine.setLayoutParams(new LayoutParams(3,
						LayoutParams.MATCH_PARENT));
					rg.addView(tvLine);
					break;
				case 2:
					drawable = R.drawable.star_24_off;
					break;
				case 3:
					drawable = R.drawable.hand_24_off;
					break;
				case 4:
					drawable = R.drawable.heart_24_off;
					break;
			}
			radio.setCompoundDrawablesWithIntrinsicBounds(
				getResources().getDrawable(drawable),
				null, null, null);
		}
		rowViews.add(radio);
		radio.setWidth(100);
		if (isBeyond) {
			/**
			 * 所有单选按钮的宽度之和超出屏幕宽度的3/4
			 */
			// 矩阵百分比
			radio.setLayoutParams(new LinearLayout.LayoutParams(
				(maxCWidth * 2 / 3 - 10)
					/ rColmns.size(),
				LinearLayout.LayoutParams.WRAP_CONTENT));
			radio.setWidth((maxCWidth * 2 / 3 - 10)
				/ rColmns.size());
		} else {
			// System.out.println("设置了Radio的宽度");
			radio.setWidth((maxCWidth * 2 / 3 - 10)
				/ rColmns.size());
		}
		// radio.setHeight(100);
		vs.add(radio);
		// radio.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Toasts.makeText(ma, "您选择了:"+t,
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		// 单选矩阵选项排斥
		if ("true".equals(colItem.exclude)
			&& !Util.isEmpty(colItem.excludeIn)) {
			String excludeIn = colItem.excludeIn;
			String[] excludeValue = excludeIn.split(",");
			if (excludeValue.length > 0) {
				for (int i = 0; i < excludeValue.length; i++) {
					if (Integer.parseInt(excludeValue[i]
						.trim()) == (rowItem
						.getItemValue())) {
						radio.setChecked(false);
						radio.setVisibility(View.INVISIBLE);
					}
				}
			}
		}
	}
	
	
}

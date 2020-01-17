package com.erajiezhang.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.erajie.global.ARouterPath;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;

/**
 * 测试图片旋转放大缩小
 * @author EraJieZhang
 * @data 2020/2/3
 */
@Route(path = ARouterPath.ShowImageActivity)
public class ShowImageActivity extends BaseActivity implements View.OnClickListener {
	
	Button big,small,clockwise,anticlockwise;
	ImageView imgshowimg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showimage_layout);
		
		imgshowimg = findViewById(R.id.img_showimg);
		big = findViewById(R.id.btn_big);
		small = findViewById(R.id.btn_small);
		clockwise = findViewById(R.id.btn_clockwise);
		anticlockwise = findViewById(R.id.btn_anticlockwise);
		
		big.setOnClickListener(this);
		small.setOnClickListener(this);
		clockwise.setOnClickListener(this);
		anticlockwise.setOnClickListener(this);
		
	}
	int count = 0;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		    case R.id.btn_big:
		        break;
			case R.id.btn_small:
				break;
			case R.id.btn_clockwise:
				count++;
				setRotate(90*count);
				break;
			case R.id.btn_anticlockwise:
				count--;
				setRotate(90*count);
				break;
		    default:
		        break;
		}
	}
	
	public void setRotate(int degrees){
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_test);
		//创建一个与bitmap一样大小的bitmap2
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas = new Canvas(bitmap2);
		//主要以这个对象调用旋转方法
		Matrix matrix = new Matrix();
		//以图片中心作为旋转中心，旋转180°
		matrix.setRotate(degrees, bitmap2.getWidth() / 2, bitmap2.getHeight() / 2);
		Paint paint = new Paint();
		//设置抗锯齿,防止过多的失真
		paint.setAntiAlias(true);
		canvas.drawBitmap(bitmap, matrix, paint);
		//将旋转后的图片设置到界面上
		imgshowimg.setImageBitmap(bitmap2);
	}
	

	private static void substring(){
		String str = "<font color='red'>*</font>Q3. <img src=upload/attach/1580690905550_live2d.png >";
		String sss = str.substring(str.indexOf("src=")+1,str.indexOf(">"));
		System.out.println(sss);
	}
	
	
	
}

package com.erajiezhang.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.erajie.rxutils.RxPhotoTool;
import com.erajie.rxutils.view.RxToast;
import com.erajiezhang.R;
import com.erajiezhang.base.BaseActivity;
import com.erajiezhang.util.PermissionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author EraJieZhang
 * @data 2019/1/2
 */
public class DomeActivity extends BaseActivity {
	@BindView(R.id.btn1_dome)
	Button mBtn1Dome;
	@BindView(R.id.bt2_dome)
	Button mBt2Dome;
	@BindView(R.id.btn3_dome)
	Button mBtn3Dome;
	@BindView(R.id.btn4_dome)
	Button mBtn4Dome;
	@BindView(R.id.tv_dome_title)
	TextView mTvDomeTitle;
	@BindView(R.id.img_dome_code)
	ImageView mImgDomeCode;
	
	private int REQUESTCODE = 1001;
	/**
	 * 图片路径
	 */
	private String imgpath = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dome);
		ButterKnife.bind(this);
		mTvDomeTitle.setText(R.string.string_title1);
		
	}
	
	
	@OnClick({R.id.btn1_dome, R.id.bt2_dome, R.id.btn3_dome, R.id.btn4_dome})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.btn1_dome:
				
				PermissionUtil.isPermission(mActivity, REQUESTCODE, "android.permission.CAMERA", new PermissionUtil.Operation() {
					@SuppressLint("MissingPermission")
					@Override
					public void OnNext() {
						RxToast.info("开始你的表演");
					}
				});
				break;
			case R.id.bt2_dome:
				RxPhotoTool.openCameraImage(mActivity);
				break;
			case R.id.btn3_dome:
				RxPhotoTool.openLocalImage(mActivity);
				break;
			case R.id.btn4_dome:
//				String replace  = url.replace(  "file://", "");
//			    Bitmap bitmap BitmapFactory. decodeFile(replace)
//				WatermarkUtil.drawTextToRightBottom(mActivity,);
				break;
		}
	}
	
	
	
	/**
	 * 返回操作
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
			case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
				if ( resultCode == RESULT_OK ) {
					FromPhone(data);
				}
				
				break;
			case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
				if ( resultCode == RESULT_OK ) {
					ByCamera(data);
				}
				break;
			case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
				RequestOptions options = new RequestOptions().placeholder(R.drawable.default_image)
					//异常占位图(当加载异常的时候出现的图片)
					.error(R.drawable.default_image)
					//禁止Glide硬盘缓存缓存
					.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
				
				Glide.with(mActivity).
					load(RxPhotoTool.cropImageUri).
					apply(options).
					thumbnail(0.5f).
					into(mImgDomeCode);
				break;
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 选择相册之后的处理
	 *
	 * @param data
	 */
	private void FromPhone(Intent data) {
		
		imgpath = RxPhotoTool.getRealPathFromUri(this, data.getData());
		showimg();
	}
	
	/**
	 * 选择照相机之后的处理
	 *
	 * @param data
	 */
	private void ByCamera(Intent data) {
		
		Cursor cursor = getContentResolver().query(RxPhotoTool.imageUriFromCamera, null, null, null, null);
		if ( cursor != null && cursor.moveToFirst() ) {
			imgpath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
		}
		showimg();
	}
	
	/**
	 * 显示图片
	 */
	private void showimg() {
		
		RequestOptions options = new RequestOptions().placeholder(R.drawable.default_image)
			//异常占位图(当加载异常的时候出现的图片)
			.error(R.drawable.default_image)
			//禁止Glide硬盘缓存缓存
			.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
		
		Glide.with(mActivity).
			load(imgpath).
			apply(options).
			thumbnail(0.5f).
			into(mImgDomeCode);
	}
	
	
}



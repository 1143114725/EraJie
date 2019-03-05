package com.erajie.rxutils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * 动态权限申请工具类
 * Created by EEH on 2018/1/19.
 */
//  PermissionUtil.isPermission(mActivity,REQUESTCODE,"android.permission.CALL_PHONE",new PermissionUtil.Operation(){
//  @SuppressLint("MissingPermission")
//  @Override
//  public void OnNext() {
//      startActivity(intent);
//  }
//  });

//String [] press = new String[]{"android.permission.RECORD_AUDIO",Manifest.permission.READ_EXTERNAL_STORAGE};
public class PermissionUtil {
    private static final String TAG = "PermissionUtil";

    /**
     * 申请一个权限
     * @param activity
     * @param requestCode  请求码
     * @param Permission    需要申请的权限
     * @param operation     权限申请之后的回调事件
     */
    public static void isPermission(Activity activity,int requestCode,String Permission,PermissionUtil.Operation operation){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "isPermission: 当前系统高于或等于6.0");
            //当前系统大于等于6.0
            if (ContextCompat.checkSelfPermission(activity, Permission) == PackageManager.PERMISSION_GRANTED) {
                //具有权限，直接操作
                operation.OnNext();
            } else {
                //不具有拍照权限，需要进行权限申请
                //String[] 权限的集合
                ActivityCompat.requestPermissions(activity, new String[]{Permission}, requestCode);
            }
        } else {
            Log.i(TAG, "isPermission: 当前系统小于6.0");
            operation.OnNext();
        }
    }

    /**
     * 申请多个权限
     * @param activity
     * @param requestCode  请求码
     * @param Permission    需要申请的权限的集合
     * @param operation     权限申请之后的回调事件
     */
    public static void isPermissions(Activity activity,int requestCode,String[] Permission,PermissionUtil.Operation operation){
//        String pers[] = new String[Permission.length];
        int index = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "isPermission: 当前系统高于或等于6.0");
            for (int i = 0; i < Permission.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, Permission[i]) != PackageManager.PERMISSION_GRANTED) {
                    Permission[index] = Permission[i];
                    index ++;
                }
            }
            if (index == 0){
                //具有权限，直接操作
                operation.OnNext();
            }else {
                ActivityCompat.requestPermissions(activity, Permission, requestCode);
            }

        } else {
            Log.i(TAG, "isPermission: 当前系统小于6.0");
            operation.OnNext();
        }
    }

    /**
     *没有权限的时候弹出对话框让用户去设置里开启权限
     * @param context
     * @param message  对话框提示内容
     * @param listener  点击取消的时候的响应事件
     */
    public static void showMessageOKCancel(final Activity context, String message,DialogInterface.OnClickListener listener) {
        if (message.equals("")){
            message = "请前往设置界面打开相应权限！";
        }
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                        intent.setData(uri);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel",listener)
                .create()
                .show();

    }

    /**
     * 回调接口
     */
    public interface Operation{
        void OnNext();
    }
}

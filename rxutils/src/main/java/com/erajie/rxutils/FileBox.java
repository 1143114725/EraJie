package com.erajie.rxutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author EraJieZhang
 * @data 2020/3/19
 */
public class FileBox {


    private static String signImage = "signImage";

    /**
     * 将文件保存到沙盒中
     *  注意：
     *  1. 这里的文件操作不再需要申请权限
     *  2. 沙盒中新建文件夹只能再系统指定的子文件夹中新建
     * @param context
     * @param fileName
     * @param bitmap
     */
    public static void saveSignImageBox(Context context,String fileName, Bitmap bitmap) {

        try {
            //图片沙盒文件夹
            File PICTURES = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imageFileDirctory = new File(PICTURES + "/" + signImage);
            if (imageFileDirctory.exists()) {
                File imageFile = new File(PICTURES + "/" + signImage + "/" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } else if (imageFileDirctory.mkdir()) {
                //如果该文件夹不存在，则新建
                //new一个文件
                File imageFile = new File(PICTURES + "/" + signImage + "/" + fileName);
                //通过流将图片写入
                FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
        }
    }

    //查询沙盒中的指定图片
    //先指定哪个沙盒子文件夹，再指定名称
    public Bitmap querySignImageBox(Context context,String environmentType,String fileName) {
        if (RxDataTool.isEmpty(fileName)){ return null;}
        Bitmap bitmap = null;
        try {
            //指定沙盒文件夹
            File picturesFile = context.getExternalFilesDir(environmentType);
            if (picturesFile != null && picturesFile.exists() && picturesFile.isDirectory()) {
                File[] files = picturesFile.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory() && file.getName().equals(signImage)) {
                            File[] signImageFiles = file.listFiles();
                            if (signImageFiles != null) {
                                for (File signImageFile : files) {
                                    String signFileName = signImageFile.getName();
                                    if (signImageFile.isFile() && fileName.equals(signFileName)) {
                                        bitmap = BitmapFactory.decodeFile(signImageFile.getPath());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return bitmap;
    }


}

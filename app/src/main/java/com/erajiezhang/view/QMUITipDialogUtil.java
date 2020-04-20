package com.erajiezhang.view;

import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * @author EraJieZhang
 * @data 2020/4/4
 */
public class QMUITipDialogUtil {

    /**
     * 展示无图标的TipDialog
     * @param view  获取上下文
     * @param tipword   显示文字
     * @param delayMillis   显示时长（毫秒）
     */
    public static void showDialogNoIcon(View view,String tipword,long delayMillis){
        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord(tipword)
                .create();
        tipDialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
            }
        }, delayMillis);
    }

    /**
     * 展示无图标的TipDialog
     * @param view  获取上下文
     * @param tipword   显示文字
     */
    public static void showDialogNoIcon(View view,String tipword){
        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord(tipword)
                .create();
        tipDialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
            }
        }, 2000);
    }

}

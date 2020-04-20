package com.erajiezhang.viewmodle;

import android.view.View;

import androidx.databinding.Bindable;

import com.erajie.base.BaseViewModle;
import com.erajiezhang.BR;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
//文字颜色


/**
 * @author EraJieZhang
 * @data 2020/4/3
 */
public class BarViewModleBean extends BaseViewModle {

    /**
     * 显示的标题
     */
    public String title;
    /**
     * 显示菜单文字
     */
    public String menuText;
    /**
     * 返回图标
     */
    public int backIcon;
    /**
     * 菜单图标
     */
    public int menuIcon;
    /**
     * 图标颜色
     */
    public int iconColor;
    /**
     * 文字颜色
     */
    public int textColor;
    /**
     * 标题文字大小
     */
    public int titleTextSize;
    /**
     * 菜单文字大小
     */
    public int menuTextSize;
    /**
     * 是否显示标题
     */
    public int titleVisibility;
    /**
     * 是否显示返回图标
     */
    public int backIconVisibility;
    /**
     * 是否显示菜单图标
     */
    public int menuIconVisibility;
    /**
     * 是否显示图标文字
     */
    public int menuTextVisibility;


    @Bindable
    public String getTitle() {

        return title;
    }

    @Bindable
    public String getMenuText() {

        return menuText;
    }

    @Bindable
    public int getBackIcon() {

        return backIcon;
    }

    @Bindable
    public int getMenuIcon() {

        return menuIcon;
    }

    @Bindable
    public int getIconColor() {

        return iconColor;
    }

    @Bindable
    public int getTextColor() {

        return textColor;
    }

    @Bindable
    public int getTitleTextSize() {

        return titleTextSize;
    }

    @Bindable
    public int getMenuTextSize() {

        return menuTextSize;
    }

    @Bindable
    public int getTitleVisibility() {

        return titleVisibility;
    }

    @Bindable
    public int getBackIconVisibility() {

        return backIconVisibility;
    }

    @Bindable
    public int getMenuIconVisibility() {

        return menuIconVisibility;
    }

    @Bindable
    public int getMenuTextVisibility() {

        return menuTextVisibility;
    }

    public void setTitle(String title) {

        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public void setMenuText(String menuText) {

        this.menuText = menuText;
        notifyPropertyChanged(BR.menuText);
    }

    public void setBackIcon(int backIcon) {

        this.backIcon = backIcon;
        notifyPropertyChanged(BR.backIcon);

    }

    public void setMenuIcon(int menuIcon) {

        this.menuIcon = menuIcon;
        notifyPropertyChanged(BR.menuIcon);

    }

    public void setIconColor(int iconColor) {

        this.iconColor = iconColor;
        notifyPropertyChanged(BR.iconColor);
    }

    public void setTextColor(int textColor) {

        this.textColor = textColor;
        notifyPropertyChanged(BR.textColor);
    }

    public void setTitleTextSize(int titleTextSize) {

        this.titleTextSize = titleTextSize;
        notifyPropertyChanged(BR.titleTextSize);
    }

    public void setMenuTextSize(int menuTextSize) {

        this.menuTextSize = menuTextSize;
        notifyPropertyChanged(BR.menuTextSize);
    }

    public void setTitleVisibility(int titleVisibility) {

        this.titleVisibility = titleVisibility;
        notifyPropertyChanged(BR.titleVisibility);
    }

    public void setBackIconVisibility(int backIconVisibility) {

        this.backIconVisibility = backIconVisibility;
        notifyPropertyChanged(BR.backIconVisibility);
    }

    public void setMenuIconVisibility(int menuIconVisibility) {

        this.menuIconVisibility = menuIconVisibility;
        notifyPropertyChanged(BR.menuIconVisibility);
    }

    public void setMenuTextVisibility(int menuTextVisibility) {

        this.menuTextVisibility = menuTextVisibility;
        notifyPropertyChanged(BR.menuTextVisibility);
    }

    @Override
    public String toString() {

        return "BarViewModleBean{" + "title='" + title + '\'' + ", menuText='" + menuText + '\'' + ", backIcon=" + backIcon + ", menuIcon=" + menuIcon + ", iconColor=" + iconColor + ", textColor=" + textColor + ", titleTextSize=" + titleTextSize + ", menuTextSize=" + menuTextSize + ", titleVisibility=" + titleVisibility + ", backIconVisibility=" + backIconVisibility + ", menuIconVisibility=" + menuIconVisibility + ", menuTextVisibility=" + menuTextVisibility + '}';
    }

    /**
     * 点击返回图标
     * @param view
     */
    public void onClickBack(View view) {
        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord("返回图标")
                .create();
        tipDialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
            }
        }, 2000);
    }

    /**
     * 点击文字菜单
     * @param view
     */
    public void onClickMenuText(View view) {
        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord("点击文字菜单")
                .create();
        tipDialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
            }
        }, 2000);

    }
    /**
     * 点击菜单图标
     * @param view
     */
    public void onClickMenuIcon(View view) {

        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord("图标菜单")
                .create();
        tipDialog.show();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
            }
        }, 2000);
    }

    /**
     * 点击标题
     */
    public void onClickTitle(View view) {

        QMUITipDialog tipDialog= new QMUITipDialog.Builder(view.getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                .setTipWord("标题")
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

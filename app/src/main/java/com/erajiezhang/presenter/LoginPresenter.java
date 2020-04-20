package com.erajiezhang.presenter;

import android.app.Activity;
import android.view.View;

import com.erajie.arout.BaseArouteUtil;
import com.erajie.global.ARouterPath;
import com.erajie.global.PresetData;
import com.erajie.rxutils.RxDataTool;
import com.erajie.rxutils.RxLogTool;
import com.erajie.rxutils.SpUtil;
import com.erajiezhang.db.DbManager;
import com.erajiezhang.db.UserDB;
import com.erajiezhang.db.dbhelp.UserDBDao;
import com.erajiezhang.view.QMUITipDialogUtil;
import com.erajiezhang.viewmodle.LoginViewModleBean;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.List;

/**
 * @author EraJieZhang
 * @data 2020/4/1
 */

public class LoginPresenter {

    public LoginPresenter() {

    }

    Activity mActivity;
    LoginViewModleBean mloginViewModleBean;

    public LoginPresenter(Activity activity, LoginViewModleBean loginViewModleBean) {

        mActivity = activity;
        mloginViewModleBean = loginViewModleBean;
    }

    /**
     * 显示密码
     * @param view
     */
    public void onClickShowPsd(View view){
        /*默认密码不可见*/
        int type = 129;
        if(mloginViewModleBean.getPassworType() == type){
            mloginViewModleBean.setPassworType(128);
        }else{
            mloginViewModleBean.setPassworType(type);
        }
    }


    /**
     * 登录事件
     * @param view
     */
    public void onClickLogin(View view) {


        String account = mloginViewModleBean.getAccount();
        String password = mloginViewModleBean.getPassword();

        if(RxDataTool.isEmpty(account)){
            mloginViewModleBean.setErrorMsg("账号不可为空！");
            return;

        }
        if(RxDataTool.isEmpty(password)){
            mloginViewModleBean.setErrorMsg("密码不可为空！");
            return;
        }

        List<UserDB> userDBList = DbManager.getDaoMaster(mActivity).newSession().getUserDBDao().queryBuilder().where(
                UserDBDao.Properties.Username.eq(account),
                UserDBDao.Properties.Passworld.eq(password),
                UserDBDao.Properties.Enable.eq("1")).build().list();

        for (int i = 0, size = userDBList.size(); i < size; i++) {
            RxLogTool.v(userDBList.toString());
        }

        if(RxDataTool.isEmpty(userDBList)){
            mloginViewModleBean.setErrorMsg("账号或密码错误！");
            return;
        }

        QMUITipDialog tipDialog= new QMUITipDialog.Builder(mActivity)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("登录成功")
                .create();
        tipDialog.show();

        SpUtil.setParam(mActivity, PresetData.IsLogin,true);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipDialog.dismiss();
                BaseArouteUtil.returnActivity(ARouterPath.Mainacitvity,ARouterPath.GROUP_MAIN);
            }
        }, 2000);

    }

    /**
     * 点击忘记密码
     * @param view
     */
    public void onClickForgetPsd(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"忘记密码");

    }

    /**
     * 用手机验证码登录
     * @param view
     */
    public void onClickChangePhoneLogin(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"用手机验证码登录");

    }
    /**
     * 用户协议
     * @param view
     */
    public void onClickAgreement(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"用户协议");

    }
    /**
     * 隐私政策
     * @param view
     */
    public void onClickPrivacypolicy(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"隐私政策");

    }
    /**
     * 跳过登录
     * @param view
     */
    public void onClickReturnLogin(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"跳过登录");

    }
    /**
     * 微信登录
     * @param view
     */
    public void onClickAroutWX(View view){

        QMUITipDialogUtil.showDialogNoIcon(view,"微信登录");

    }


}

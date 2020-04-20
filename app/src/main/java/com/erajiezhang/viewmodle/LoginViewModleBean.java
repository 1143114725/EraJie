package com.erajiezhang.viewmodle;

import androidx.databinding.Bindable;

import com.erajie.base.BaseViewModle;
import com.erajiezhang.BR;

/**
 * @author EraJieZhang
 * @data 2020/3/31
 */
public class LoginViewModleBean extends BaseViewModle {

    /**
     * 显示标题文字
     */
    public String showTitle;
    /**
     * 用户名
     */
    public String account;
    /**
     * 密码
     */
    public String password;
    /**
     * 错误提示信息
     */
    public String errorMsg;
    /**
     * 忘记密码
     */
    public String forget;
    /**
     *切换手机号登陆
     */
    public String changePhoneLogin;
    /**
     * 登陆
     */
    public String login;
    /**
     * 跳过
     */
    public String returnLogin;
    /**
     * 密码类型
     */
    public int passworType;
    @Bindable
    public int getPassworType() {

        return passworType;
    }

    @Bindable
    public String getShowTitle() {

        return showTitle;
    }
    @Bindable
    public String getAccount() {

        return account;
    }
    @Bindable
    public String getPassword() {

        return password;
    }
    @Bindable
    public String getErrorMsg() {

        return errorMsg;
    }
    @Bindable
    public String getForget() {

        return forget;
    }
    @Bindable
    public String getChangePhoneLogin() {

        return changePhoneLogin;
    }
    @Bindable
    public String getLogin() {

        return login;
    }
    @Bindable
    public String getReturnLogin() {

        return returnLogin;
    }

    public void setShowTitle(String showTitle) {

        this.showTitle = showTitle;
        notifyPropertyChanged(BR.showTitle);

    }

    public void setAccount(String account) {

        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    public void setPassword(String password) {

        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void setErrorMsg(String errorMsg) {

        this.errorMsg = errorMsg;
        notifyPropertyChanged(BR.errorMsg);
    }

    public void setPassworType(int passworType) {

        this.passworType = passworType;
        notifyChangeAll();
    }
}

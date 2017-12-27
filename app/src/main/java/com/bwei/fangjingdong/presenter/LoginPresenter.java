package com.bwei.fangjingdong.presenter;

import android.text.TextUtils;

import com.bwei.fangjingdong.bean.LoginBean;
import com.bwei.fangjingdong.model.LoginModel;
import com.bwei.fangjingdong.model.imodel.ILoginModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.ILoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：王建勋
 * 时间：2017-12-09 16:15
 * 类的用途：
 */

public class LoginPresenter {

    private final ILoginModel iLoginModel;
    private final ILoginActivity iLoginActivity;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
        iLoginModel = new LoginModel();
    }

    public void getLogin() {
        String account = iLoginActivity.getAccount();
        String pwd = iLoginActivity.getpwd();
        //去调用model，进行登陆
        iLoginModel.getLogin(account, pwd, new OnNetListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                //保存登陆成功后的数据，可以保存到SP,也可以保存到数据库
                String code = loginBean.getCode();
                String msg = loginBean.getMsg();
                iLoginActivity.showLogin(loginBean, code, msg);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}

package com.bwei.fangjingdong.presenter;

import android.text.TextUtils;

import com.bwei.fangjingdong.bean.RegisterBean;
import com.bwei.fangjingdong.model.RegisterModel;
import com.bwei.fangjingdong.model.imodel.IRegisterModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IRegisterActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：王建勋
 * 时间：2017-12-12 13:41
 * 类的用途：
 */

public class RegisterPrersenter {

    private final IRegisterModel iRegisterModel;
    private final IRegisterActivity iRegisterActivity;

    public RegisterPrersenter(IRegisterActivity iRegisterActivity) {
        this.iRegisterActivity = iRegisterActivity;
        iRegisterModel = new RegisterModel();
    }

    public void register() {
        String account = iRegisterActivity.getAccount();
        String pwd = iRegisterActivity.getPwd();

        iRegisterModel.register(account, pwd, new OnNetListener<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                String code = registerBean.getCode();
                String msg = registerBean.getMsg();
                iRegisterActivity.showRegister(code, msg);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

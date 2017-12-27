package com.bwei.fangjingdong.presenter;

import com.bwei.fangjingdong.bean.UserInfoBean;
import com.bwei.fangjingdong.model.UserModel;
import com.bwei.fangjingdong.model.imodel.IUserModel;
import com.bwei.fangjingdong.net.OnNetListener;
import com.bwei.fangjingdong.view.iview.IUserView;

/**
 * 作者：王建勋
 * 时间：2017-12-12 21:04
 * 类的用途：
 */

public class UserPresenter {

    private final IUserModel iUserModel;
    private final IUserView iUserView;

    public UserPresenter(IUserView iUserView) {
        this.iUserView = iUserView;
        iUserModel = new UserModel();
    }

    public void getUser(String uid){
        iUserModel.getUser(uid, new OnNetListener<UserInfoBean>() {
            @Override
            public void onSuccess(UserInfoBean userInfoBean) {
                iUserView.showUserInfo(userInfoBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

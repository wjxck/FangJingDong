package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.bean.LoginBean;
import com.bwei.fangjingdong.presenter.LoginPresenter;
import com.bwei.fangjingdong.utils.MyApp;
import com.bwei.fangjingdong.view.iview.ILoginActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginActivity {

    private ImageView mLoginBack;
    /**
     * 手机号
     */
    private EditText mEtPhone;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    private ImageView mImgPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 请注册
     */
    private TextView mTvRegister;
    private ImageView mImgWeixin;
    private ImageView mImgMqq;
    private LinearLayout mActivityLogin;
    private LoginPresenter loginPresenter;
    String codes;
    String msgs;


    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //关联presenter
        loginPresenter = new LoginPresenter(this);
        initView();

        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());
    }

    private void initView() {
        mLoginBack = (ImageView) findViewById(R.id.login_back);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mImgPwd = (ImageView) findViewById(R.id.img_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mImgWeixin = (ImageView) findViewById(R.id.img_weixin);
        mImgMqq = (ImageView) findViewById(R.id.img_mqq);
        mActivityLogin = (LinearLayout) findViewById(R.id.activity_login);
        mLoginBack.setOnClickListener(this);
        mImgWeixin.setOnClickListener(this);
        mImgMqq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_back:
                //返回
                this.finish();
                break;
            case R.id.btn_login:
                //登录
                loginPresenter.getLogin();
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                intent1.putExtra("flag", 5);
                startActivity(intent1);
                break;
            case R.id.tv_register:
                //注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.img_weixin:
                //微信登录

                break;
            case R.id.img_mqq:
                //QQ登录
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this, "all", mIUiListener);
                break;
        }
    }


    @Override
    public String getAccount() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getpwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void showLogin(LoginBean loginBean, String code, String msg) {
        codes = code;
        msgs = msg;
        if (codes.equals("0")) {
            Toast.makeText(this, msgs, Toast.LENGTH_SHORT).show();
            LoginBean.DataBean data = loginBean.getData();
            int uid = data.getUid();
            String name = data.getUsername();
            String token = data.getToken();
            MyApp.edit.putInt("uid", uid);
            MyApp.edit.putString("name", name);
            MyApp.edit.putString("token", token);
            MyApp.edit.putBoolean("flag", true);
            MyApp.edit.commit();
            finish();
        } else {
            MyApp.edit.putBoolean("flag", false);
            MyApp.edit.commit();
            Toast.makeText(this, msgs, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                        Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                        intent2.putExtra("flag", 5);
                        startActivity(intent2);
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

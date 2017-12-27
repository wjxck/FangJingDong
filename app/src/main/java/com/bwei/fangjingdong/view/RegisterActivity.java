package com.bwei.fangjingdong.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.fangjingdong.R;
import com.bwei.fangjingdong.presenter.RegisterPrersenter;
import com.bwei.fangjingdong.view.iview.IRegisterActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterActivity {

    private ImageView mRegisterBack;
    /**
     * 手机号
     */
    private EditText mEtPhone;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    /**
     * 注册
     */
    private Button mBtnRegister;
    private RegisterPrersenter registerPrersenter;
    String codes;
    String msgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPrersenter = new RegisterPrersenter(this);
        initView();

    }

    private void initView() {
        mRegisterBack = (ImageView) findViewById(R.id.register_back);
        mRegisterBack.setOnClickListener(this);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.register_back:
                //返回
                this.finish();
                break;
            case R.id.btn_register:
                //注册
                registerPrersenter.register();
                break;
        }
    }

    @Override
    public String getAccount() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void showRegister(String code, String msg) {
        codes = code;
        msgs = msg;
        if (codes.equals("0")) {
            Toast.makeText(this, msgs, Toast.LENGTH_SHORT).show();
            if (msgs.equals("注册成功")) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, msgs, Toast.LENGTH_SHORT).show();
        }
    }

}

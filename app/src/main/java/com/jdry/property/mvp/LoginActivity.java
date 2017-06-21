package com.jdry.property.mvp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jdry.property.mvp.presenter.LoginPresenter;
import com.jdry.property.mvp.view.IView;
import com.jdry.property.property.R;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JDRY_SJM on 2017/4/17.
 */

public class LoginActivity extends JdryBaseActivity implements IView {
    @BindView(R.id.iv_phone_img)
    ImageView ivPhoneImg;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.iv_pwd)
    ImageView ivPwd;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    @OnClick(R.id.btnLogin)
    public void loginClick() {
        String userName = etUserName.getText().toString();
        if (null == userName || "".equals(userName)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString();
        if (null == pwd || "".equals(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginPresenter loginPresenter = new LoginPresenter(this, this);
        loginPresenter.getLoginInfo(userName, pwd);
    }

    @OnClick(R.id.checkbox)
    public void onViewClicked() {
        Toast.makeText(this, "保存密码", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreateByMe(Bundle savedInstanceState) {
        init();
    }

    @Override
    public int getResouceId() {
        return R.layout.activity_login;
    }

    private void init() {

    }
}

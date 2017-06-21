package com.jdry.property.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.jdry.property.bean.LoginBean;
import com.jdry.property.mvp.LoginActivity;
import com.jdry.property.mvp.MainActivity;
import com.jdry.property.mvp.model.ILogin;
import com.jdry.property.mvp.model.LoginModel;
import com.jdry.property.mvp.model.IOnLoginListener;
import com.jdry.property.mvp.view.IView;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class LoginPresenter {
    public ILogin iLogin;
    public IView iShowView;
    public Context context;

    public LoginPresenter(Context context, IView iShowView) {
        this.iShowView = iShowView;
        this.context = context;
        this.iLogin = new LoginModel();
    }

    public void getLoginInfo(String name, String pwd) {
        this.iShowView.showProgress();
        this.iLogin.login(name, pwd, new IOnLoginListener() {
            @Override
            public void loginSuccess(LoginBean loginBeanBackup) {
                Toast.makeText(context, loginBeanBackup.getMessage(), Toast.LENGTH_SHORT).show();
                iShowView.hideProgress();
                LoginActivity activity = (LoginActivity) context;
                activity.openNewActivity(MainActivity.class);
            }
            @Override
            public void loginFailure() {
                iShowView.hideProgress();
            }
        });
    }
}

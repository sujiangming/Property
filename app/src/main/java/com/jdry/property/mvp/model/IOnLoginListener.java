package com.jdry.property.mvp.model;

import com.jdry.property.bean.LoginBean;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public interface IOnLoginListener {
    void loginSuccess(LoginBean loginBean);
    void loginFailure();
}

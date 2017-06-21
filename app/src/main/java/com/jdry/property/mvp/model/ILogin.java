package com.jdry.property.mvp.model;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public interface ILogin {
    public void login(String name, String pwd,IOnLoginListener listener);
}

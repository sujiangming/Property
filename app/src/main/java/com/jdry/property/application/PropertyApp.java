package com.jdry.property.application;

import android.app.Application;

import com.jdry.property.bean.LoginBean;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class PropertyApp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);//初始化LitePal数据库
        initLoginBean();
    }

    //初始化单例
    private void initLoginBean() {
        LoginBean.getInstance().load();
    }
}

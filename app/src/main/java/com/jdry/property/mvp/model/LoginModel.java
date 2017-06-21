package com.jdry.property.mvp.model;

import com.alibaba.fastjson.JSON;
import com.jdry.property.bean.LoginBean;
import com.jdry.property.config.Config;
import com.jdry.property.http.HttpRequestUtil;
import com.jdry.property.http.IService;
import com.jdry.property.http.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class LoginModel implements ILogin {

    @Override
    public void login(String name, String pwd, final IOnLoginListener listener) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("username", name);
        hashMap.put("password", pwd);
        String url = HttpRequestUtil.getLoginUrl(Config.LOGIN_CLS, Config.LOGIN_METHOD, JSON.toJSONString(hashMap));
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        IService iService = retrofitUtil.createReq(IService.class);
        Call<LoginBean> loginBeanCall = iService.login(url);
        loginBeanCall.enqueue(new Callback<LoginBean>() { //异步调用
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.isSuccessful()) {
                    LoginBean loginBean = response.body();
                    LoginBean.getInstance().setValueByLoginDataJson(loginBean);
                    listener.loginSuccess(loginBean);
                } else {
                    listener.loginFailure();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                listener.loginFailure();
            }
        });
    }
}

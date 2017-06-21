package com.jdry.property.mvp.model;

import android.util.Log;

import com.jdry.property.bean.LoginBean;
import com.jdry.property.bean.WrapperRspEntity;
import com.jdry.property.config.Config;
import com.jdry.property.http.HttpRequestUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public class GetContactModel implements IGetContact<WrapperRspEntity> {
    @Override
    public void getContactInfo(Callback<WrapperRspEntity> callback) {
        Call<WrapperRspEntity> call = HttpRequestUtil.httpRequest(Config.CONTACT_CLS, Config.CONTACT_METHOD, LoginBean.getInstance().getToken(), null);
        call.enqueue(callback);
    }
}

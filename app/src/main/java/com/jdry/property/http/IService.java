package com.jdry.property.http;

import com.jdry.property.bean.LoginBean;
import com.jdry.property.bean.WrapperRspEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by JDRY_SJM on 2017/4/17.
 */

public interface IService {
    @POST
    Call<LoginBean> login(@Url String url);

    @POST
    Call<WrapperRspEntity> httpRequest(@Url String url);
}

package com.jdry.property.mvp.model;

import retrofit2.Callback;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public interface IGetContact<WrapperRspEntity> {
    void getContactInfo(Callback<WrapperRspEntity> callback);
}

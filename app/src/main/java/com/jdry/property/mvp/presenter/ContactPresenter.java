package com.jdry.property.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.jdry.property.bean.WrapperRspEntity;
import com.jdry.property.mvp.model.GetContactModel;
import com.jdry.property.mvp.model.IGetContact;
import com.jdry.property.mvp.model.ILogin;
import com.jdry.property.mvp.model.LoginModel;
import com.jdry.property.mvp.view.IView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public class ContactPresenter {
    public IGetContact iGetContact;
    public IView iView;
    public Context context;

    public ContactPresenter(Context context, IView iShowView) {
        this.iView = iShowView;
        this.context = context;
        this.iGetContact = new GetContactModel();
    }

    public void getContactInfo() {
        iView.showProgress();
        iGetContact.getContactInfo(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                iView.hideProgress();
                WrapperRspEntity wrapperRspEntity = (WrapperRspEntity) response.body();
                Log.d("wrapperRspEntity",wrapperRspEntity.toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}

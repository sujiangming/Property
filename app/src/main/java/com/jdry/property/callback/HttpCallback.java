package com.jdry.property.callback;

import com.jdry.property.http.IOnListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class HttpCallback<T> implements Callback<T> {

    public IOnListener<T> onListener;

    public HttpCallback(IOnListener<T> onListener) {
        this.onListener = onListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            T t = response.body();
            onListener.success(t);
        } else {
            onListener.failure();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onListener.failure();
    }
}

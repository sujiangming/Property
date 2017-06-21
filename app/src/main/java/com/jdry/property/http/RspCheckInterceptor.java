package com.jdry.property.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class RspCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        try {
            ResponseBody rspBody = response.body();
            JSONObject jsonObject = JSON.parseObject(rspBody.string());
            int status = jsonObject.getIntValue("status");
            if (status < 200 || status >= 300) {
                throw new IOException(jsonObject.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("parase data error");
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            }
        }

        return response;
    }
}

package com.jdry.property.http;

import com.alibaba.fastjson.JSON;
import com.jdry.property.bean.LoginBean;
import com.jdry.property.bean.WrapperRspEntity;

import org.litepal.crud.DataSupport;

import retrofit2.Call;

/**
 * Created by JDRY_SJM on 2017/4/18.
 */

public class HttpRequestUtil {
    public static String getLoginUrl(String cls, String method, String param) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("access.app?")
                .append("clsname=" + cls + "&")
                .append("methodname=" + method + "&data=" + param);
        return stringBuffer.toString();
    }

    public static String getUrl(String cls, String method, String token, String param) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("access.app?")
                .append("clsname=" + cls + "&")
                .append("methodname=" + method)
                .append("&token=" + token);
        if (null != param && !"".equals(param)) {
            stringBuffer.append("&data=" + param);
        }
        return stringBuffer.toString();
    }

    public static Call<WrapperRspEntity> httpRequest(String cls, String method, String token, Object map) {
        String url = getUrl(cls, method, token, map == null ? null : JSON.toJSONString(map));
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        IService iService = retrofitUtil.createReq(IService.class);
        Call<WrapperRspEntity> call = iService.httpRequest(url);
        return call;
    }

    public static String getToken() {
        LoginBean loginBeanBackup = LoginBean.getInstance();
        if (null == loginBeanBackup) {
            return null;
        }
        return loginBeanBackup.getToken();
    }
}

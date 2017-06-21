package com.jdry.property.http;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public interface IOnListener<T> {
    void success(T T);
    void failure();
}

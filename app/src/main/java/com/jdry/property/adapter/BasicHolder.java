package com.jdry.property.adapter;

import android.view.View;

import java.util.List;

/**
 * Created by JDRY_SJM on 2017/4/21.
 */

public abstract class BasicHolder<T> {
    public View holderView;
    public List<T> mLists;

    public BasicHolder(List<T> mLists) {
        this.mLists = mLists;
        holderView = getInflateView();
    }

    public abstract View getInflateView();

    public abstract void bindData(int position);
}

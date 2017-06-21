package com.jdry.property.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdry.property.mvp.view.IView;
import com.jdry.property.property.R;
import com.jdry.property.util.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by JDRY_SJM on 2017/4/17.
 */

public abstract class JdryBaseFragment extends Fragment implements IView {

    private Unbinder unbinder;
    protected Context mContext;
    protected View mRootView;

    private AppManager appManager = AppManager.getAppManager();

    private JdryProgressBar jdryProgressBar;

    public abstract int getResourceId();

    protected abstract void onCreateViewByMe(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResourceId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        this.mContext = getActivity();
        onCreateViewByMe(savedInstanceState);
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void openNewActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void openNewActivityByIntent(Class<?> cls, Intent intent) {
        if (null == intent) {
            return;
        }
        intent.setClass(mContext, cls);
        startActivity(intent);
    }

    @Override
    public void openNewActivity(Class<?> cls) {
        openNewActivity(cls, null);
    }

    @Override
    public void closeActivity() {
        appManager.finishActivity();
    }

    @Override
    public void showProgress() {
        if (null == this.jdryProgressBar) {
            jdryProgressBar = JdryProgressBar.show(mContext);
        }
    }

    @Override
    public void hideProgress() {
        if (null != jdryProgressBar && jdryProgressBar.isShowing()) {
            jdryProgressBar.dismiss();
        }
    }
}

package com.jdry.property.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jdry.property.mvp.view.IView;
import com.jdry.property.util.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JDRY_SJM on 2017/4/17.
 */

public abstract class JdryBaseActivity extends AppCompatActivity implements IView {
    public abstract int getResouceId();

    protected abstract void onCreateByMe(Bundle savedInstanceState);

    private Unbinder unbinder;

    private AppManager appManager = AppManager.getAppManager();

    private JdryProgressBar jdryProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResouceId());
        unbinder = ButterKnife.bind(this);
        onCreateByMe(savedInstanceState);
        appManager.addActivity(this);
    }

    @Override
    public void openNewActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
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
    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        if (null == this.jdryProgressBar) {
            jdryProgressBar = JdryProgressBar.show(this);
        }
    }

    @Override
    public void hideProgress() {
        if (null != jdryProgressBar && jdryProgressBar.isShowing()) {
            jdryProgressBar.dismiss();
        }
    }
}

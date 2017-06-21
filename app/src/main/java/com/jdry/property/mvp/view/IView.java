package com.jdry.property.mvp.view;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by JDRY_SJM on 2017/4/17.
 */

public interface IView {
    void showProgress();

    void hideProgress();

    void openNewActivity(Class<?> cls, Bundle bundle);

    void openNewActivityByIntent(Class<?> cls, Intent intent);

    void openNewActivity(Class<?> cls);

    void closeActivity();
}

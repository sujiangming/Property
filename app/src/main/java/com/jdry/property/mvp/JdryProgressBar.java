package com.jdry.property.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.jdry.property.property.R;

/**
 * Created by Administrator on 2016/9/9.
 */
public class JdryProgressBar extends ProgressDialog{
        public JdryProgressBar(Context context) {
            super(context);
        }
        public JdryProgressBar(Context context, int theme) {
            super(context,theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_jdry_progress_dialog);

        }
        public static JdryProgressBar show (Context context) {
            JdryProgressBar jdryProgressBar = new JdryProgressBar(context, R.style.JdryProgressbar);
            jdryProgressBar.setCanceledOnTouchOutside(false);
            jdryProgressBar.show();
            return jdryProgressBar;
        }
}

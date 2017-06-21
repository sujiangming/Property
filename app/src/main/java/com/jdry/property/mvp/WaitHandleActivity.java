package com.jdry.property.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jdry.property.other.CircleRefreshLayout;
import com.jdry.property.property.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JDRY_SJM on 2017/5/2.
 */

public class WaitHandleActivity extends JdryBaseActivity {
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.refresh_layout)
    CircleRefreshLayout refreshLayout;
    @BindView(R.id.stop_refresh)
    Button stopRefresh;

    @Override
    public int getResouceId() {
        return R.layout.activity_wait_handle;
    }

    @Override
    protected void onCreateByMe(Bundle savedInstanceState) {
        String[] strs = {
                "The",
                "Canvas",
                "class",
                "holds",
                "the",
                "draw",
                "calls",
                ".",
                "To",
                "draw",
                "something,",
                "you",
                "need",
                "4 basic",
                "components",
                "Bitmap",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
        list.setAdapter(adapter);

        stopRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayout.finishRefreshing();
            }
        });

        refreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        // do something when refresh starts
                    }

                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete
                    }
                });
    }

    @OnClick(R.id.stop_refresh)
    public void onViewClicked() {

    }
}

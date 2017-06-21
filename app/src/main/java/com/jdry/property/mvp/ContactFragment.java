package com.jdry.property.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jdry.property.mvp.presenter.ContactPresenter;
import com.jdry.property.other.CustomPinnedHeaderListView;
import com.jdry.property.other.CustomPinnedHeaderListViewAdapter;
import com.jdry.property.property.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JDRY_SJM on 2017/4/20.
 */

public class ContactFragment extends JdryBaseFragment {

    @BindView(R.id.lv)
    CustomPinnedHeaderListView listView;

    private CustomPinnedHeaderListViewAdapter adapter;

    private View headerView;

    private View footerView;

    @Override
    public int getResourceId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void onCreateViewByMe(Bundle savedInstanceState) {
        initView();
        ContactPresenter contactPresenter = new ContactPresenter(getContext(),this);
        contactPresenter.getContactInfo();
    }

    public void initView() {
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.cus_pinned_header_lv_header, null);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.cus_pinned_header_lv_footer, null);
        listView.addHeaderView(headerView);
        listView.addFooterView(footerView);
        adapter = new CustomPinnedHeaderListViewAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnMyItemClickListener(new CustomPinnedHeaderListView.MyOnItemClickListener() {
            @Override
            public void onSectionItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
                Toast.makeText(getActivity(), "s:" + section + "    item:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
                Toast.makeText(getActivity(), "s:" + section, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHeaderClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), "header:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFooterClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), "footer:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

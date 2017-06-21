package com.jdry.property.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdry.property.bean.LoginBean;
import com.jdry.property.property.R;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends JdryBaseActivity {

    @BindView(R.id.ll_fragment_container)
    LinearLayout llFragmentContainer;
    @BindView(R.id.iv_contact)
    ImageView ivContact;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_triangle)
    TextView tvTriangle;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.ll_user)
    LinearLayout llUser;

    @OnClick({R.id.ll_hot, R.id.ll_home, R.id.ll_user, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_hot:
                changeNavStyle(view);
                break;
            case R.id.ll_home:
                changeNavStyle(view);
                break;
            case R.id.ll_user:
                changeNavStyle(view);
                break;
            case R.id.tv_set:
                openNewActivity(LoginActivity.class);
                break;
        }
    }

    private FragmentManager fragmentManager;
    private ContactFragment contactFragment = null;
    private HomeFragment homeFragment = null;
    private UserFragment userFragment = null;

    private final int CLICK_TEXT_COLOR = 0xFF209E85;
    private final int UNCLICK_TEXT_COLOR = 0xFFFFFFFF;

    private LoginBean dataBean = null;
    private int index = 2;

    @Override
    public int getResouceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateByMe(Bundle savedInstanceState) {
        dataBean = LoginBean.getInstance();
        init();
    }

    private void init() {
        initFragments();
    }

    private void initFragments() {
        fragmentManager = getSupportFragmentManager();
        changeFragment(index);//显示主页
    }

    private void changeFragment(int indexTmp) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (indexTmp) {
            case 1:
                if (null == contactFragment) {
                    contactFragment = new ContactFragment();
                    transaction.add(R.id.ll_fragment_container, contactFragment);
                } else {
                    transaction.show(contactFragment);
                }
                break;
            case 2:
                if (null == homeFragment) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.ll_fragment_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 3:
                if (null == userFragment) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.ll_fragment_container, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != contactFragment) {
            transaction.hide(contactFragment);
        }
        if (null != homeFragment) {
            transaction.hide(homeFragment);
        }
        if (null != userFragment) {
            transaction.hide(userFragment);
        }
    }

    private void changeNavStyle(View view) {
        String tag = (String) view.getTag();
        index = Integer.parseInt(tag);
        changeFragment(index);
        switch (index) {
            case 1:
                llLeftContainer.setBackgroundResource(R.color.indexColorNormal);
                tvTriangle.setBackgroundResource(R.drawable.triangle_left);
                ivContact.setImageResource(R.mipmap.btn_phone_c_2_3x);
                tvContact.setTextColor(UNCLICK_TEXT_COLOR);
                ivHome.setImageResource(R.mipmap.btn_home_c_2_3x);
                tvHome.setTextColor(UNCLICK_TEXT_COLOR);
                llUser.setBackgroundResource(R.color.indexColorChange);
                ivUser.setImageResource(R.mipmap.btn_user3x);
                tvUser.setTextColor(CLICK_TEXT_COLOR);
                break;
            case 2:
                llLeftContainer.setBackgroundResource(R.color.indexColorNormal);
                tvTriangle.setBackgroundResource(R.drawable.triangle_left);
                ivHome.setImageResource(R.mipmap.btn_home3x);
                tvHome.setTextColor(UNCLICK_TEXT_COLOR);
                ivContact.setImageResource(R.mipmap.btn_phone3x);
                tvContact.setTextColor(UNCLICK_TEXT_COLOR);
                llUser.setBackgroundResource(R.color.indexColorChange);
                ivUser.setImageResource(R.mipmap.btn_user3x);
                tvUser.setTextColor(CLICK_TEXT_COLOR);
                break;
            case 3:
                llLeftContainer.setBackgroundResource(R.color.indexColorChange);
                tvTriangle.setBackgroundResource(R.drawable.triangle_right);
                llUser.setBackgroundResource(R.color.indexColorNormal);
                ivUser.setImageResource(R.mipmap.btn_user_c3x);
                tvUser.setTextColor(UNCLICK_TEXT_COLOR);
                ivHome.setImageResource(R.mipmap.btn_home_c3x);
                tvHome.setTextColor(CLICK_TEXT_COLOR);
                ivContact.setImageResource(R.mipmap.btn_phone_c3x);
                tvContact.setTextColor(CLICK_TEXT_COLOR);
                break;
        }
    }
}

package com.jdry.property.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdry.property.bean.HomeItemBean;
import com.jdry.property.bean.LoginBean;
import com.jdry.property.property.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by JDRY_SJM on 2017/4/20.
 */

public class HomeFragment extends JdryBaseFragment {
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_title)
    TextView tvUserTitle;
    @BindView(R.id.ll_home_item)
    LinearLayout llHomeItem;
    @BindView(R.id.tv_no_auth)
    TextView tvNoAuth;
    @BindView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;

    private LoginBean loginBean = LoginBean.getInstance();

    @Override
    protected void onCreateViewByMe(Bundle savedInstanceState) {
        initHomeItem(savedInstanceState);
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_home;
    }

    private void initHomeItem(Bundle savedInstanceState) {
        if(null == loginBean){
            tvUserTitle.setText("无部门 " + " ● " + " 无职位");
            tvUserName.setText("无名人士");
            return;
        }
        LoginBean.DataBean dataBean = loginBean.getData();
        if(null == dataBean){
            return;
        }
        String cName = dataBean.getCName();
        String deptName = dataBean.getDeptName();
        String positionName = dataBean.getPositionName();
        if (null == deptName && null == positionName) {// 秩序维护部 ● 安保人员
            tvUserTitle.setText("无部门 " + " ● " + " 无职位");
        } else {
            tvUserTitle.setText(deptName + " ● " + positionName);
        }
        tvUserName.setText(cName == null ? "无名人士" : cName);
        List<LoginBean.DataBean.AuthBean> authBeanList = loginBean.getData().getAuth();
        if (null == authBeanList || authBeanList.size() == 0) {
            tvNoAuth.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.GONE);
            return;
        }
        for (LoginBean.DataBean.AuthBean authBean : authBeanList) {
            LinearLayout view = (LinearLayout) getLayoutInflater(savedInstanceState).inflate(R.layout.home_item, null);
            ImageView imageView = (ImageView) view.getChildAt(0);
            TextView textView = (TextView) view.getChildAt(1);
            HomeItemBean homeItemBean = getHomeItem(authBean);
            if (null != homeItemBean) {
                imageView.setImageResource(homeItemBean.imgResource);
                textView.setText(homeItemBean.resName);
                llHomeItem.addView(view);
            }
        }
    }

    private HomeItemBean getHomeItem(LoginBean.DataBean.AuthBean authBean) {
        HomeItemBean homeItemBean = null;
        String name = authBean.getName();
        switch (name) {
            case "公告管理"://orderNo="1"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.notice;
                break;
            case "咨询建议"://orderNo="2"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.home_btn_consult3x;
                break;
            case "维修服务"://orderNo="3"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.rp;
                break;
            case "收费管理"://orderNo="4"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.pay;
                break;
            case "活动管理"://orderNo="5"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.activity_manager;
                break;
            case "论坛管理"://orderNo="6"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.bbs;
                break;
            case "家政服务"://orderNo="7"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.hk;
                break;
            case "巡视巡检"://orderNo="8"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.patrol;
                break;
            case "移动监控"://orderNo="9"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.monitor;
                break;
            case "人员定位"://orderNo="10"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.position;
                break;
            case "访客管理"://orderNo="11"
                homeItemBean = new HomeItemBean();
                homeItemBean.resName = name;
                homeItemBean.imgResource = R.mipmap.visitor;
                break;
        }
        return homeItemBean;
    }

    @OnClick({R.id.ll_wait, R.id.ll_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wait:
                openNewActivity(WaitHandleActivity.class);
                break;
            case R.id.ll_msg:
                break;
        }
    }
}

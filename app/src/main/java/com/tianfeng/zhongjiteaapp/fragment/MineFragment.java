package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.AboutActivity;
import com.tianfeng.zhongjiteaapp.activity.HelpActivity;
import com.tianfeng.zhongjiteaapp.activity.MyCollectedActivity;
import com.tianfeng.zhongjiteaapp.activity.SettingActivity;
import com.tianfeng.zhongjiteaapp.activity.ShopInformationActivity;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.iv_head_photo)
    CircleImageView ivHeadPhoto;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.rl_shop)
    RelativeLayout rlShop;
    @Bind(R.id.rl_collect)
    RelativeLayout rlCollect;
    @Bind(R.id.rl_share)
    RelativeLayout rlShare;
    @Bind(R.id.rl_help)
    RelativeLayout rlHelp;
    @Bind(R.id.rl_about)
    RelativeLayout rlAbout;
    @Bind(R.id.rl_setting)
    RelativeLayout rlSetting;
    @Bind(R.id.tv_exit)
    TextView tvExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine, null);
        UIUtils.setBarTint(getActivity(), false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_shop, R.id.rl_collect, R.id.rl_share, R.id.rl_help, R.id.rl_about, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_shop:
                openActivity(ShopInformationActivity.class,null);
                break;
            case R.id.rl_collect:
                openActivity(MyCollectedActivity.class,null);
                break;
            case R.id.rl_share:

                break;
            case R.id.rl_help:
                openActivity(HelpActivity.class,null);
                break;
            case R.id.rl_about:
                openActivity(AboutActivity.class,null);
                break;
            case R.id.rl_setting:
                openActivity(SettingActivity.class,null);
                break;
        }
    }
}

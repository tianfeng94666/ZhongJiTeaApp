package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.AboutActivity;
import com.tianfeng.zhongjiteaapp.activity.ChooseShopDialogActivity;
import com.tianfeng.zhongjiteaapp.activity.HelpActivity;
import com.tianfeng.zhongjiteaapp.activity.HistrotyMessageActivity;
import com.tianfeng.zhongjiteaapp.activity.LoginAcitivity;
import com.tianfeng.zhongjiteaapp.activity.MyCollectedActivity;
import com.tianfeng.zhongjiteaapp.activity.OrderActivity;
import com.tianfeng.zhongjiteaapp.activity.SettingActivity;
import com.tianfeng.zhongjiteaapp.activity.ShopInformationActivity;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.bean.ShareContent;
import com.tianfeng.zhongjiteaapp.json.LogoutResult;
import com.tianfeng.zhongjiteaapp.net.ImageLoadOptions;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CircleImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

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
    @Bind(R.id.rl_order)
    RelativeLayout rlOrder;
    @Bind(R.id.rootview)
    ScrollView rootview;
    @Bind(R.id.iv_message)
    ImageView ivMessage;
    private SharedPopupWindow sharedPopupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mine, null);
        UIUtils.setBarTint(getActivity(), false);
        sharedPopupWindow = new SharedPopupWindow(getActivity());
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initView();
        }
    }

    private void initView() {
        ImageLoader.getInstance().displayImage(Global.HeadView, ivHeadPhoto, ImageLoadOptions.getOptions());
        tvUsername.setText(Global.nickName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rl_shop, R.id.rl_collect, R.id.rl_share, R.id.rl_help, R.id.rl_about, R.id.rl_setting, R.id.tv_exit, R.id.rl_order, R.id.iv_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_shop:
                if (StringUtils.isEmpty(Global.shopId)) {
                    openActivity(ChooseShopDialogActivity.class, null);
                } else {
                    openActivity(ShopInformationActivity.class, null);
                }

                break;
            case R.id.rl_collect:
                openActivity(MyCollectedActivity.class, null);
                break;
            case R.id.rl_order:
                openActivity(OrderActivity.class, null);
                break;
            case R.id.rl_share:
                share();
                break;
            case R.id.rl_help:
                openActivity(HelpActivity.class, null);
                break;
            case R.id.rl_about:
                openActivity(AboutActivity.class, null);
                break;
            case R.id.rl_setting:
                openActivity(SettingActivity.class, null);
                break;
            case R.id.tv_exit:
                logout();
                break;
            case R.id.iv_message:
                openActivity(HistrotyMessageActivity.class, null);
                break;
        }
    }

    private void share() {
        ShareContent shareContent = new ShareContent();
        shareContent.setImagUrl("");
        shareContent.setUrl("https://www.pgyer.com/n1z5");
        shareContent.setTitle("小茶宝下载");
        shareContent.setText("一款关于各种精品茶叶的app");
        sharedPopupWindow.showPop(rootview, shareContent);
    }

    private void logout() {
        Map map = new HashMap();
        String url = AppURL.LOGOUT_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(getActivity(), url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                LogoutResult logoutResult = new Gson().fromJson(result, LogoutResult.class);
                if (Global.RESULT_CODE.equals(logoutResult.getCode())) {
                    Global.isLogin = false;
                    SpUtils.getInstace(getActivity()).saveBoolean("isExit", true);
                    openActivity(LoginAcitivity.class, null);
                    getActivity().finish();

                } else {
                    showToastReal(logoutResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }
}

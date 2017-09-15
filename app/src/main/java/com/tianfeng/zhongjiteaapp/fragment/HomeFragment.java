package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.ProductActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.fl_flybanner)
    FrameLayout flFlybanner;
    @Bind(R.id.tv_notice)
    TextView tvNotice;
    @Bind(R.id.tv_new_tea)
    TextView tvNewTea;
    @Bind(R.id.lv_new_tea)
    CustomLV lvNewTea;
    @Bind(R.id.tv_hot_tea)
    TextView tvHotTea;
    @Bind(R.id.lv_hot_tea)
    CustomLV lvHotTea;
    SharedPopupWindow sharedPopupWindow;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        initView(view);
        sharedPopupWindow = new SharedPopupWindow(getActivity());
        return view;

    }

    private void initView(final View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        List list2 = new ArrayList();
        list2.add(R.mipmap.one);
        list2.add(R.mipmap.two);
        list2.add(R.mipmap.three);
        flybanner.setImages(list2);
        lvHotTea.setFocusable(false);
        lvNewTea.setFocusable(false);
        lvHotTea.setAdapter(new CommonAdapter<String>(list, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastManager.showToastReal("搜藏");
                    }
                });
                helper.setViewOnclick(R.id.iv_item_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPopupWindow.showPop(view);
                    }
                });
            }
        });
        lvHotTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity(ProductActivity.class, null);
            }
        });
        lvNewTea.setAdapter(new CommonAdapter<String>(list, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastManager.showToastReal("搜藏");
                    }
                });
                helper.setViewOnclick(R.id.iv_item_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPopupWindow.showPop(view);
                    }
                });
            }
        });
        lvNewTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity(ProductActivity.class, null);
            }
        });

        UIUtils.setListViewHeightBasedOnChildren(lvHotTea);
        UIUtils.setListViewHeightBasedOnChildren(lvNewTea);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

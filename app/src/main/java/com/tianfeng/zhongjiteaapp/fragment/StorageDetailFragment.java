package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 田丰 on 2017/9/3.
 */

public class StorageDetailFragment extends BaseFragment {
    @Bind(R.id.lv_mall_product)
    CustomLV lvMallProduct;
    int type = 0;//0云南仓，1华南仓

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_strage_detail, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {

        List<String> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            list.add("1");
        }
        lvMallProduct.setAdapter(new CommonAdapter<String>(list,R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

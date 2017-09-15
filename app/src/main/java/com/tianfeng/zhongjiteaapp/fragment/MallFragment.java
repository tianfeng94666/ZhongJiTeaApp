package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.ProductActivity;
import com.tianfeng.zhongjiteaapp.activity.SearchTeaActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class MallFragment extends BaseFragment {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.lv_mall_product)
    CustomLV lvMallProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_mall, null);
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        netLoad();
        initView(view);
        return view;
    }

    private void netLoad() {
        Map map = new HashMap();
        map.put("index", 0);
        map.put("pageSize",10);
        map.put("goodsName","");
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);


            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }

    private void initView(View view) {
        idIgBack.setVisibility(View.GONE);
        titleText.setText("商城");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        lvMallProduct.setAdapter(new CommonAdapter<String>(list, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {

            }
        });
        lvMallProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity(ProductActivity.class, null);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_search)
    public void onClick() {
        openActivity(SearchTeaActivity.class,null);
    }
}

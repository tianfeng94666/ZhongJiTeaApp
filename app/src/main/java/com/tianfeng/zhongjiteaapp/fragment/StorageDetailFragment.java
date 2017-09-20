package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.StorageTradeActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.CollectedResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 田丰 on 2017/9/3.
 */

public class StorageDetailFragment extends BaseFragment {
    @Bind(R.id.lv_mall_product)
    ListView lvMallProduct;
    int type = 0;//1云南仓，0华南仓
    private int index;

    public StorageDetailFragment(int i) {
        this.type =i;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_strage_detail, null);
        ButterKnife.bind(this, view);
        initView(view);
        getData();
        return view;
    }

    private void getData() {
        Map map = new HashMap();
        map.put("index", index);
        map.put("pageSize", 10);
        if(type==0){
            map.put("deportId", "0001");
        }else {
            map.put("deportId", "0000");
        }

        L.e("map", map.toString());
        L.e("url=", AppURL.STORAGE_LIST_URL);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.STORAGE_LIST_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
//                CollectedResult collectedResult = new Gson().fromJson(result, CollectedResult.class);
//                if (Global.RESULT_CODE.equals(collectedResult.getCode())) {
//
//                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                ToastManager.showToastReal(fail);
            }
        }, map);
    }

    private void initView(View view) {

        final List<String> list = new ArrayList<>();

        list.add("小茶宝服务协议");
        list.add("仓储管理服务协议");
        list.add("仓储类问题");
        list.add("茶叶质押协议");
        list.add("茶叶赎回协议");

        lvMallProduct.setAdapter(new CommonAdapter<String>(list,R.layout.item_storage) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {

            }
        });
        lvMallProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity(StorageTradeActivity.class,null);
            }
        });
//        UIUtils.setListViewHeightBasedOnChildren(lvMallProduct);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

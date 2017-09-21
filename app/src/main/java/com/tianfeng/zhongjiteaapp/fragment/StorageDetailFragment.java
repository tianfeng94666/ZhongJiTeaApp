package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.PledgeActivity;
import com.tianfeng.zhongjiteaapp.activity.StorageTradeActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.StorageResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 田丰 on 2017/9/3.
 */

public class StorageDetailFragment extends BaseFragment implements XListView.IXListViewListener{
    @Bind(R.id.lv_mall_product)
    XListView lvMallProduct;
    int type = 0;//1云南仓，0华南仓
    private int index=1;
    int maxIndex;
    private StorageResult storageResult;
    private List<OrderBean> storageList=new ArrayList<>();
    private CommonAdapter<OrderBean> storageAdapter;

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
        if(type==1){
            map.put("storeType", "0001");
        }else {
            map.put("storeType", "0002");
        }

        L.e("map", map.toString());
        L.e("url=", AppURL.STORAGE_LIST_URL);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.STORAGE_LIST_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                 storageResult = new Gson().fromJson(result, StorageResult.class);
                if (Global.RESULT_CODE.equals(storageResult.getCode())) {
                        if(storageResult.getResult()!=null&&storageResult.getResult().getResult()!=null){
                            List<OrderBean>    temp = storageResult.getResult().getResult();
                            maxIndex =storageResult.getResult().getTotalPage();
                            if(temp.size()>0){
                                if(maxIndex>= index){
                                    storageList.addAll(temp);
                                    setData();
                                }
                            }
                        }
                }else {
                    ToastManager.showToastReal(storageResult.getMsg());
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                ToastManager.showToastReal(fail);
            }
        }, map);
    }

    private void setData() {
        if(storageAdapter==null){
            storageAdapter = new CommonAdapter<OrderBean>(storageList,R.layout.item_storage) {
                @Override
                public void convert(int position, BaseViewHolder helper, final OrderBean item) {
                    helper.setText(R.id.tv_item_name,item.getGoodsName());
                    helper.setText(R.id.tv_item_tag,item.getTagName());
                    helper.setText(R.id.tv_item_type,item.getTypeName());
                    helper.setText(R.id.tv_item_price,"茶叶单价："+item.getPrice());
                    helper.setText(R.id.tv_amount,"成交量："+item.getQuantity());
                    helper.setText(R.id.tv_total_money,"成交总金额："+item.getTotal());
                    helper.setText(R.id.tv_date,"购买时间："+item.getEndTime());
                    helper.setText(R.id.iv_item_state, CommMethod.getState(item.getTransStatus()));
                    helper.setImageBitmap(R.id.iv_item_product,AppURL.baseHost+"/"+item.getImgUrl());

                    helper.getView(R.id.iv_item_state).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            judge(item);

                        }
                    });
                }


            };
            lvMallProduct.setAdapter(storageAdapter);
            lvMallProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("storageItem",storageList.get(i-1));
                    openActivity(StorageTradeActivity.class,bundle);
                }
            });
        }else {
            storageAdapter.notifyDataSetChanged();
        }

    }

    private void judge(OrderBean item) {
        int state = Integer.parseInt(item.getTransStatus());
        Bundle bundle = new Bundle();
        switch (state) {
            //已暂存
            case 1:case 3 :case 6:
                bundle.putSerializable("storageItem",item);
                openActivity(PledgeActivity.class,bundle);
                break;
            case 8:
                bundle.putSerializable("storageItem",item);
                openActivity(StorageTradeActivity.class,bundle);
                break;
            default:
                ToastManager.showToastReal("该状态订单不能操作");
                break;
        }
    }

    private void initView(View view) {

        lvMallProduct.setXListViewListener(this);
        lvMallProduct.setAutoLoadEnable(false);
        lvMallProduct.setPullRefreshEnable(false);
        lvMallProduct.setPullLoadEnable(true);


//        UIUtils.setListViewHeightBasedOnChildren(lvMallProduct);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        index++;
        getData();
    }
}

package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.OrderResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class OrderActivity extends BaseActivity implements XListView.IXListViewListener{
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_order)
    XListView lvOrder;
    @Bind(R.id.ll_rootview)
    LinearLayout llRootview;
    private int index=1;
    private int maxIndex;
    private OrderResult getOrderResult;
    private List<OrderResult.ResultBeanX.ResultBean> orderList=new ArrayList<>();
    private CommonAdapter<OrderResult.ResultBeanX.ResultBean> orderAdapter;
    private SharedPopupWindow sharedPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        UIUtils.setBarTint(this,false);
        sharedPopupWindow = new SharedPopupWindow(this);
        initView();
        getOrder();
    }

    private void initView() {
        lvOrder.setXListViewListener(this);
        lvOrder.setAutoLoadEnable(false);
        lvOrder.setPullRefreshEnable(false);
        lvOrder.setPullLoadEnable(true);
        titleText.setText("我的订单");
    }
    private void getOrder() {
        Map map = new HashMap();
        map.put("index", index + "");
        map.put("pageSize", 10);
        VolleyRequestUtils.getInstance().getRequestPost(this, AppURL.ORDER_SEARCH, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result--", result);
                getOrderResult = new Gson().fromJson(result, OrderResult.class);
                if (Global.RESULT_CODE.equals(getOrderResult.getCode())) {
                    maxIndex = getOrderResult.getResult().getTotalPage();
                    List temp = getOrderResult.getResult().getResult();
                    if(maxIndex>=index){
                        orderList.addAll(temp);
                    }else {
                        showToastReal("已经是全部数据了");
                    }
                    setLv();
                } else {
                    showToastReal(getOrderResult.getMsg());
                }
                lvOrder.stopRefresh();
                lvOrder.stopLoadMore();
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                lvOrder.stopRefresh();
                lvOrder.stopLoadMore();
            }
        }, map);
    }

    private void setLv() {
        if (orderAdapter == null) {
            orderAdapter = new CommonAdapter<OrderResult.ResultBeanX.ResultBean>(orderList, R.layout.item_order) {
                @Override
                public void convert(int position, BaseViewHolder helper, final OrderResult.ResultBeanX.ResultBean item) {
                    helper.setText(R.id.tv_item_name,item.getGoodsName());

                    if(StringUtils.isEmpty(item.getLogistics())){
                        helper.getView(R.id.tv_logistics).setVisibility(View.GONE);
                        helper.getView(R.id.tv_logistics_number).setVisibility(View.GONE);
                    }else {
                        helper.getView(R.id.tv_logistics).setVisibility(View.VISIBLE);
                        helper.getView(R.id.tv_logistics_number).setVisibility(View.VISIBLE);
                    }
                    helper.setText(R.id.tv_logistics,"物流公司："+item.getLogistics());
                    helper.setText(R.id.tv_logistics_number,"订单号："+item.getLogisticsNumber());
//                    helper.setText(R.id.tv_item_tag," "+item.getTagName()+" ");
                    helper.setText(R.id.tv_item_type,item.getDeportName()+item.getTypeName());
                    helper.setText(R.id.tv_item_price,"茶叶单价："+item.getPrice());
                    helper.setText(R.id.tv_amount,"成交量："+item.getQuantity());
                    helper.setText(R.id.tv_total_money,"成交总金额："+item.getTotal());
                    helper.setText(R.id.tv_date,"购买时间："+CommMethod.getFormatedDateTime(item.getCreateTime()));
                    helper.setText(R.id.iv_item_state,item.getOrderTypeName());
                    helper.setImageBitmap(R.id.iv_item_product,AppURL.baseHost+"/"+item.getImgUrl());
                }




            };
            lvOrder.setAdapter(orderAdapter);
        } else {
            orderAdapter.notifyDataSetChanged();
        }

    }





    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        index++;
        getOrder();
    }

}

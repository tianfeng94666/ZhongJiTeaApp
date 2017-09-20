package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.tianfeng.zhongjiteaapp.json.MyCollectedResult;
import com.tianfeng.zhongjiteaapp.json.Product;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class MyCollectedActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_mycollected)
    ListView lvMycollected;
    @Bind(R.id.ll_rootview)
    LinearLayout llRootview;
    private MyCollectedResult mycollected;
    private CommonAdapter<Product> collectedAdapter;
    private List<Product> collectedList = new ArrayList<>();
    private SharedPopupWindow sharedPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        ButterKnife.bind(this);
        sharedPopupWindow = new SharedPopupWindow(this);
        initView();
        netload();
    }

    public void netload() {
        Map map = new HashMap();
        map.put("userId", Global.UserId);

        String url = AppURL.MY_COLLETED;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                mycollected = new Gson().fromJson(result, MyCollectedResult.class);
                if (Global.RESULT_CODE.equals(mycollected.getCode())) {
                    if (mycollected.getResult() != null) {
                        List<Product> temp = mycollected.getResult();
                        if (temp.size() > 0) {
                            collectedList.addAll(temp);
                            setData();
                        }

                    }

                } else {
                    showToastReal(mycollected.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
            }
        }, map);
    }

    private void setData() {
        if (collectedAdapter == null) {
            setAdapter();
        } else {
            collectedAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
//        lvMycollected.setXListViewListener(this);
//        lvMycollected.setAutoLoadEnable(false);
//        lvMycollected.setPullRefreshEnable(false);
//        lvMycollected.setPullLoadEnable(true);
        idIgBack.setVisibility(View.VISIBLE);
        titleText.setText("我的收藏");

    }

    private void setAdapter() {
        collectedAdapter = new CommonAdapter<Product>(collectedList, R.layout.item_product) {
            @Override
            public void convert(final int position, final BaseViewHolder helper, final Product item) {
                helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                helper.setText(R.id.tv_item_name, item.getGoodsName());
                helper.setText(R.id.tv_item_type, item.getDeportName());
                if (StringUtils.isEmpty(item.getTagName())) {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.GONE);
                } else {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.VISIBLE);
                }

                helper.setImageResource(R.id.iv_item_collection, R.mipmap.collected);

                helper.setText(R.id.tv_item_tag, item.getTagName());
                helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        helper.setImageResource(R.id.iv_item_collection, R.mipmap.uncollected);
                        CommMethod.uncollected(MyCollectedActivity.this, item.getId());
                        collectedList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                helper.setViewOnclick(R.id.iv_item_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedPopupWindow.showPop(llRootview);
                    }
                });
            }
        };
        lvMycollected.setAdapter(collectedAdapter);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}

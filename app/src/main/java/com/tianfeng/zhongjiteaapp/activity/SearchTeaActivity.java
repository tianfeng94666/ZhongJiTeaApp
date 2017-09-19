package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.CollectedResult;
import com.tianfeng.zhongjiteaapp.json.GetProductResult;
import com.tianfeng.zhongjiteaapp.json.Product;
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
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class SearchTeaActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.et_search_tea)
    EditText etSearchTea;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.lv_seach_tea)
    XListView lvSeachTea;
    @Bind(R.id.ll_rootview)
    LinearLayout llRootview;
    private GetProductResult getProductResult;
    private List<Product> productList = new ArrayList<>();
    private int index;
    SharedPopupWindow sharedPopupWindow;
    private CommonAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tea);
        UIUtils.setBarTint(this, false);
        ButterKnife.bind(this);
        sharedPopupWindow = new SharedPopupWindow(this);
        initView();
    }

    private void initView() {
        initListener();
        lvSeachTea.setXListViewListener(this);
        lvSeachTea.setAutoLoadEnable(true);
        lvSeachTea.setPullRefreshEnable(true);
        lvSeachTea.setPullLoadEnable(true);
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        etSearchTea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getProduct();
                }
                return false;

            }
        });
    }

    private void getProduct() {
        Map map = new HashMap();
        map.put("index", index + "");
        map.put("pageSize", 10);
        map.put("goodsName", etSearchTea.getText().toString());
        VolleyRequestUtils.getInstance().getRequestPost(this, AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result--", result);
                getProductResult = new Gson().fromJson(result, GetProductResult.class);
                if (Global.RESULT_CODE.equals(getProductResult.getCode())) {
                    List temp = getProductResult.getResult().getResult();
                    productList.addAll(temp);
                    setLv();
                } else {
                    showToastReal(getProductResult.getMsg());
                }
                lvSeachTea.stopRefresh();
                lvSeachTea.stopLoadMore();
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                lvSeachTea.stopRefresh();
                lvSeachTea.stopLoadMore();
            }
        }, map);
    }

    private void setLv() {
        if (productAdapter == null) {
            productAdapter = new CommonAdapter<Product>(productList, R.layout.item_product) {
                @Override
                public void convert(int position, BaseViewHolder helper, final Product item) {
                    helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                    helper.setText(R.id.tv_item_name, item.getGoodsName());
                    helper.setText(R.id.tv_item_type, item.getDeportName());
                    if (StringUtils.isEmpty(item.getTagName())) {
                        helper.getView(R.id.tv_item_tag).setVisibility(View.GONE);
                    } else {
                        helper.getView(R.id.tv_item_tag).setVisibility(View.VISIBLE);
                    }
                    helper.setText(R.id.tv_item_tag, item.getTagName());
                    helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                    helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            collected(item.getId());
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
        } else {
            productAdapter.notifyDataSetChanged();
        }
        lvSeachTea.setAdapter(productAdapter);
    }

    /**
     * 收藏
     *
     * @param id
     */
    private void collected(String id) {
        Map map = new HashMap();
        if (StringUtils.isEmpty(Global.shopId)) {

        }
        map.put("userId", Global.UserId);
        map.put("goodsId", id);

        VolleyRequestUtils.getInstance().getRequestPost(this, AppURL.COLLECTED_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                CollectedResult collectedResult = new Gson().fromJson(result, CollectedResult.class);
                if (Global.RESULT_CODE.equals(collectedResult.getCode())) {

                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
            }
        }, map);

    }

    @OnClick(R.id.tv_cancle)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        index = 1;
        productList.clear();
        getProduct();
    }

    @Override
    public void onLoadMore() {
        index++;
        getProduct();
    }
}

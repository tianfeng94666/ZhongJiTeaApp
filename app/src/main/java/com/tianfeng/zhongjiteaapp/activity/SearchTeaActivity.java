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
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.bean.ShareContent;
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

import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

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
    private int index=1;
    private int maxIndex;
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
        lvSeachTea.setAutoLoadEnable(false);
        lvSeachTea.setPullRefreshEnable(false);
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
                    if(maxIndex>=index){
                        productList.addAll(temp);
                    }else {
                        showToastReal("已经是全部数据了");
                    }
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
                public void convert(int position, final BaseViewHolder helper, final Product item) {
                    helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                    helper.setText(R.id.tv_item_name, item.getGoodsName());
                    helper.setText(R.id.tv_item_type, item.getDeportName()+" "+item.getTypeName());
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
                            if("0".equals(item.getIsStored())){
                                item.setIsStored("1");
                                helper.setImageResource(R.id.iv_item_collection,R.mipmap.collected);
                                CommMethod.collected(SearchTeaActivity.this,item.getId());
                            }else {
                                item.setIsStored("0");
                                helper.setImageResource(R.id.iv_item_collection,R.mipmap.uncollected);
                                CommMethod.uncollected(SearchTeaActivity.this,item.getId());
                            }
                        }
                    });
                    helper.setViewOnclick(R.id.iv_item_share, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ShareContent shareContent = new ShareContent();
                            shareContent.setImagUrl(AppURL.baseHost + "/" + item.getImgUrl());
                            shareContent.setUrl(AppURL.baseHost + "/" + item.getInformationUrl());
                            shareContent.setTitle(item.getGoodsName());
                            shareContent.setText(item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                            sharedPopupWindow.showPop(llRootview,shareContent);
                        }
                    });
                }


            };
            lvSeachTea.setAdapter(productAdapter);
        } else {
            productAdapter.notifyDataSetChanged();
        }

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
    @OnClick(R.id.tv_cancle)
    public void onClick() {
        finish();
    }
}

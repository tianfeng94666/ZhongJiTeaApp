package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.ProductActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.AdResult;
import com.tianfeng.zhongjiteaapp.json.CollectedResult;
import com.tianfeng.zhongjiteaapp.json.GetProductResult;
import com.tianfeng.zhongjiteaapp.json.NoticeResult;
import com.tianfeng.zhongjiteaapp.json.Product;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

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
    private GetProductResult getProductResult;
    List<Product> newlist = new ArrayList<>();
    private int newTeaIndex, hotTeaIndex;
    private List<Product> hotlist = new ArrayList<>();
    private NoticeResult noticeResult;
    private AdResult adResult;
    private List<AdResult.ResultBeanX.ResultBean> adList;//广告
    private List<String> adUrlList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        netLoad();
        sharedPopupWindow = new SharedPopupWindow(getActivity());
        return view;

    }

    private void netLoad() {
        /**
         * 获取广告轮播
         */
        getAd();
        //获取公告
        getNotice();
        //获取新茶 02
        getProduct(newTeaIndex, "02");
        //获取热门茶
        getProduct(newTeaIndex, "01");
    }

    private void getNotice() {
        Map map = new HashMap();
        map.put("index", 1);
        map.put("pageSize", 3);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_NOTICE_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result ", result);
                L.e("url  ", AppURL.GET_NOTICE_URL);
                noticeResult = new Gson().fromJson(result, NoticeResult.class);
                if (Global.RESULT_CODE.equals(noticeResult.getCode())) {
                    tvNotice.setText(noticeResult.getResult().getResult().get(0).getTitle());

                } else {
                    showToastReal(noticeResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }

    private void getAd() {
        Map map = new HashMap();
        map.put("index", 1);
        map.put("pageSize", 3);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_ADS_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                L.e("url  ", AppURL.GET_ADS_URL);
                adResult = new Gson().fromJson(result, AdResult.class);
                if (Global.RESULT_CODE.equals(adResult.getCode())) {
                adList =   adResult.getResult().getResult();
                    if(adList!=null&&adList.size()>0){
                        for(AdResult.ResultBeanX.ResultBean itme:adList){
                           adUrlList.add(AppURL.baseHost+itme.getImgUrl());
                        }
                        flybanner.setImagesUrl(adUrlList);
                    }


                } else {
                    showToastReal(adResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }

    private void getProduct(int index, final String tag) {
        Map map = new HashMap();
        map.put("index", index + "");
        map.put("pageSize", 10);
        map.put("goodsName", "");
        map.put("tag", tag);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result--" + tag + "  ", result);
                getProductResult = new Gson().fromJson(result, GetProductResult.class);
                if (Global.RESULT_CODE.equals(getProductResult.getCode())) {
                    List temp = getProductResult.getResult().getResult();
                    if (("02").equals(tag)) {
                        newlist.addAll(temp);
                    } else {
                        hotlist.addAll(temp);
                    }

                    initView(view);
                } else {
                    showToastReal(getProductResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }

    private void initView(final View view) {
        lvHotTea.setFocusable(false);
        lvNewTea.setFocusable(false);
        lvHotTea.setAdapter(new CommonAdapter<Product>(newlist, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, Product item) {
                helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                helper.setText(R.id.tv_item_name, item.getGoodsName());
                helper.setText(R.id.tv_item_type, item.getDeportName());
                helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToastReal("搜藏");
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

        lvNewTea.setAdapter(new CommonAdapter<Product>(hotlist, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, final Product item) {
                helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                helper.setText(R.id.tv_item_name, item.getGoodsName());
                helper.setText(R.id.tv_item_type, item.getDeportName());
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

    /**
     * 收藏
     *
     * @param id
     */
    private void collected(String id) {
        Map map = new HashMap();
        if(StringUtils.isEmpty(Global.shopId)){

        }
        map.put("userId", Global.UserId);
        map.put("goodsId", id);

        VolleyRequestUtils.getInstance().getStringPostRequest(getActivity(), AppURL.COLLECTED_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

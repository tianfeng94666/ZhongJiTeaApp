package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.AdInfoActivity;
import com.tianfeng.zhongjiteaapp.activity.ChooseShopActivity;
import com.tianfeng.zhongjiteaapp.activity.ChooseShopDialogActivity;
import com.tianfeng.zhongjiteaapp.activity.DialogActivity;
import com.tianfeng.zhongjiteaapp.activity.HistoryNoticeActivity;
import com.tianfeng.zhongjiteaapp.activity.MainActivity;
import com.tianfeng.zhongjiteaapp.activity.ProductActivity;
import com.tianfeng.zhongjiteaapp.activity.StorageActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.bean.ShareContent;
import com.tianfeng.zhongjiteaapp.json.AdBean;
import com.tianfeng.zhongjiteaapp.json.AdResult;
import com.tianfeng.zhongjiteaapp.json.GetProductResult;
import com.tianfeng.zhongjiteaapp.json.NoticeResult;
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.Product;
import com.tianfeng.zhongjiteaapp.json.StorageCountResult;
import com.tianfeng.zhongjiteaapp.json.StorageResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.SharedPopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CustomLV;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;
import com.tianfeng.zhongjiteaapp.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;
import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class HomeFragment extends BaseFragment implements XListView.IXListViewListener {
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
    @Bind(R.id.ll_notice)
    LinearLayout llNotice;
    @Bind(R.id.tv_loadmore)
    TextView tvLoadmore;
    @Bind(R.id.tv_hotloadmore)
    TextView tvHotloadmore;
    @Bind(R.id.tv_buy_tea)
    TextView tvBuyTea;
    @Bind(R.id.tv_store_tea)
    TextView tvStoreTea;
    @Bind(R.id.tv_change_tea)
    TextView tvChangeTea;
    private View view;
    private GetProductResult getProductResult;
    List<Product> newlist = new ArrayList<>();
    private int newTeaIndex = 1, hotTeaIndex = 1;
    private int maxNewTeaIndex, maxHotTeaIndex;
    private List<Product> hotlist = new ArrayList<>();
    private NoticeResult noticeResult;
    private AdResult adResult;
    private List<AdBean> adList;//广告
    private List<String> adUrlList = new ArrayList<>();
    private CommonAdapter<Product> hotAdapter;
    private CommonAdapter<Product> newAdapter;
    private StorageCountResult storageResult;
    int storageCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_home, null);
        sharedPopupWindow = new SharedPopupWindow(getActivity());
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        netLoad();
        return view;

    }


    private void netLoad() {
        /**
         * 获取广告轮播
         */
//        ViewGroup.LayoutParams lp = flFlybanner.getLayoutParams();
//        lp.height = (int) (UIUtils.getWindowWidth() / 1.89);
//        flFlybanner.setLayoutParams(lp);
        getAd();
        //获取公告
        getNotice();
        //获取新茶 02
        getProduct(newTeaIndex, "02");
        //获取热门茶
        getProduct(newTeaIndex, "01");
        //获取仓储
        getStorage();
        //判断是否要弹出绑定门店
        isAssociateShop();
    }

    private void isAssociateShop() {
        int openType =((MainActivity)getActivity()).getOpenType();
        if(openType==1){
            return;
        }else {
            if(StringUtils.isEmpty(Global.shopId)){
                openActivity(ChooseShopDialogActivity.class,null);
            }
        }
    }

    private void getStorage() {
        Map map = new HashMap();
        map.put("transType", "0001");


        L.e("map", map.toString());
        L.e("url=", AppURL.GET_STORAGE);
        VolleyRequestUtils.getInstance().getStringPostRequest(getActivity(), AppURL.GET_STORAGE, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                storageResult = new Gson().fromJson(result, StorageCountResult.class);
                if (Global.RESULT_CODE.equals(storageResult.getCode())) {
                    storageCount = storageResult.getResult();
                } else {
                    ToastManager.showToastReal(storageResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                ToastManager.showToastReal(fail);

            }
        }, map);
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
                    if (noticeResult.getResult() != null && noticeResult.getResult().getResult() != null && noticeResult.getResult().getResult().size() > 0) {
                        tvNotice.setText(noticeResult.getResult().getResult().get(0).getTitle());
                    }


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
        map.put("pageSize", 5);
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_ADS_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                L.e("url  ", AppURL.GET_ADS_URL);
                adResult = new Gson().fromJson(result, AdResult.class);
                if (Global.RESULT_CODE.equals(adResult.getCode())) {
                    adList = adResult.getResult().getResult();
                    if (adList != null && adList.size() > 0) {
                        for (AdBean itme : adList) {
                            adUrlList.add(AppURL.baseHost + itme.getImgUrl());
                        }
                        initBanner();

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

    private void initBanner() {
        flybanner.setImagesUrl(adUrlList);
        flybanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("adBean", adList.get(position));
                openActivity(AdInfoActivity.class, bundle);
            }
        });
    }

    private void getProduct(int index, final String tag) {
        Map map = new HashMap();
        map.put("index", index + "");
        map.put("pageSize", 10);
        map.put("tag", tag);
        L.e("map", map.toString());
        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result--" + tag + "  ", result);
                getProductResult = new Gson().fromJson(result, GetProductResult.class);
                if (Global.RESULT_CODE.equals(getProductResult.getCode())) {
                    List temp = getProductResult.getResult().getResult();
                    if (("02").equals(tag)) {
                        maxNewTeaIndex = getProductResult.getResult().getTotalPage();
                        if (temp.size() == 0) {
                            tvNewTea.setVisibility(View.GONE);
                        } else {
                            newlist.addAll(temp);
                        }

                    } else {
                        maxHotTeaIndex = getProductResult.getResult().getTotalPage();
                        if (maxHotTeaIndex >= hotTeaIndex) {
                            hotlist.addAll(temp);
                        } else {
                            showToastReal("已经是全部数据了");
                        }
                        if (hotlist.size() == 0) {
                            tvHotloadmore.setVisibility(View.GONE);
                        } else {
                            tvHotloadmore.setVisibility(View.VISIBLE);
                        }
//                        lvHotTea.stopLoadMore();
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
//        lvHotTea.setXListViewListener(this);
//        lvHotTea.setAutoLoadEnable(true);
//        lvHotTea.setPullRefreshEnable(false);
//        lvHotTea.setPullLoadEnable(true);
        lvHotTea.setHeaderDividersEnabled(false);
        if (hotAdapter == null) {
            setHotTeaAdapter();
        } else {
            hotAdapter.notifyDataSetChanged();
        }


        lvHotTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                //Xlistview 添加了一个头部
                bundle.putSerializable("product", hotlist.get(i));
                openActivity(ProductActivity.class, bundle);
            }
        });
        if (newAdapter == null) {
            setNewTeaAdapter();
        } else {
            newAdapter.notifyDataSetChanged();
        }


        lvNewTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", newlist.get(i));
                openActivity(ProductActivity.class, bundle);
            }
        });

        UIUtils.setListViewHeightBasedOnChildren(lvHotTea);
        UIUtils.setListViewHeightBasedOnChildren(lvNewTea);

        tvHotloadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotTeaIndex++;
                getProduct(hotTeaIndex, "01");
            }
        });
        llNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("type", "1");
//                if (noticeResult != null && noticeResult.getResult() != null) {
//                    bundle.putString("title", noticeResult.getResult().getResult().get(0).getTitle());
//                    bundle.putString("content", noticeResult.getResult().getResult().get(0).getContent());
//                    openActivity(TextActivity.class, bundle);
//                }
                openActivity(HistoryNoticeActivity.class, null);


            }
        });
    }

    private void setNewTeaAdapter() {
        newAdapter = new CommonAdapter<Product>(newlist, R.layout.item_product) {
            @Override
            public void convert(int position, final BaseViewHolder helper, final Product item) {
                helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                helper.setText(R.id.tv_item_name, item.getGoodsName());
                helper.setText(R.id.tv_item_type, item.getDeportName() + " " + item.getTypeName());
                if (StringUtils.isEmpty(item.getTagName())) {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.GONE);
                } else {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.VISIBLE);
                }
                if ("0".equals(item.getIsStored())) {
                    helper.setImageResource(R.id.iv_item_collection, R.mipmap.uncollected);
                } else {
                    helper.setImageResource(R.id.iv_item_collection, R.mipmap.collected);
                }
                helper.setText(R.id.tv_item_tag, " " + item.getTagName() + " ");
                helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                helper.setViewOnclick(R.id.ll_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ("0".equals(item.getIsStored())) {
                            item.setIsStored("1");
                            helper.setImageResource(R.id.iv_item_collection, R.mipmap.collected);
                            CommMethod.collected(getActivity(), item.getId());
                        } else {
                            item.setIsStored("0");
                            helper.setImageResource(R.id.iv_item_collection, R.mipmap.uncollected);
                            CommMethod.uncollected(getActivity(), item.getId());
                        }


                    }
                });
                helper.setViewOnclick(R.id.ll_item_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareContent shareContent = new ShareContent();
                        shareContent.setImagUrl(AppURL.baseHost + "/" + item.getImgUrl());
                        shareContent.setUrl(AppURL.baseHost + "/" + item.getInformationUrl());
                        shareContent.setTitle(item.getGoodsName());
                        shareContent.setText(item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                        sharedPopupWindow.showPop(view, shareContent);
                    }
                });
            }
        };
        lvNewTea.setAdapter(newAdapter);
    }

    private void setHotTeaAdapter() {
        hotAdapter = new CommonAdapter<Product>(hotlist, R.layout.item_product) {
            @Override
            public void convert(int position, final BaseViewHolder helper, final Product item) {
                helper.setImageBitmap(R.id.iv_item_product, AppURL.baseHost + "/" + item.getImgUrl());
                helper.setText(R.id.tv_item_name, item.getGoodsName());
                helper.setText(R.id.tv_item_type, item.getDeportName() + " " + item.getTypeName());
                if (StringUtils.isEmpty(item.getTagName())) {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.GONE);
                } else {
                    helper.getView(R.id.tv_item_tag).setVisibility(View.VISIBLE);
                }
                if ("0".equals(item.getIsStored())) {
                    helper.setImageResource(R.id.iv_item_collection, R.mipmap.uncollected);
                } else {
                    helper.setImageResource(R.id.iv_item_collection, R.mipmap.collected);
                }
                helper.setText(R.id.tv_item_tag, " " + item.getTagName() + " ");
                helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                helper.setViewOnclick(R.id.ll_item_collection, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ("0".equals(item.getIsStored())) {
                            item.setIsStored("1");
                            helper.setImageResource(R.id.iv_item_collection, R.mipmap.collected);
                            CommMethod.collected(getActivity(), item.getId());
                        } else {
                            item.setIsStored("0");
                            helper.setImageResource(R.id.iv_item_collection, R.mipmap.uncollected);
                            CommMethod.uncollected(getActivity(), item.getId());
                        }
                    }
                });
                helper.setViewOnclick(R.id.ll_item_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareContent shareContent = new ShareContent();
                        shareContent.setImagUrl(AppURL.baseHost + "/" + item.getImgUrl());
                        shareContent.setUrl(AppURL.baseHost + "/" + item.getInformationUrl());
                        shareContent.setTitle(item.getGoodsName());
                        shareContent.setText(item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                        sharedPopupWindow.showPop(view, shareContent);
                    }
                });
            }
        };
        lvHotTea.setAdapter(hotAdapter);
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

        hotTeaIndex++;
        getProduct(hotTeaIndex, "01");
    }

    @OnClick({R.id.tv_buy_tea, R.id.tv_store_tea, R.id.tv_change_tea})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buy_tea:
                Bundle bundle = new Bundle();
                bundle.putString("key", "请联系附近经销商门店");
                openActivity(DialogActivity.class, bundle);
                break;
            case R.id.tv_store_tea:
                storageTea();

                break;
            case R.id.tv_change_tea:
                changeTea();
                break;
        }
    }

    private void storageTea() {
        if(StringUtils.isEmpty(Global.shopId)){
            Bundle bundle = new Bundle();
            bundle.putInt("nextStep",1);
            openActivity(ChooseShopDialogActivity.class,bundle);
        }else {
            openActivity(StorageActivity.class,null);
//            ((MainActivity) getActivity()).setChioceFragment(2);
        }
    }

    private void changeTea() {
        if (storageCount > 0) {
            ((MainActivity) getActivity()).setChioceFragment(2);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("key", "请先存储茶品");
            openActivity(DialogActivity.class, bundle);
        }
    }
}

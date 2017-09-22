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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.ProductActivity;
import com.tianfeng.zhongjiteaapp.activity.SearchTeaActivity;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.bean.ShareContent;
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

import static android.R.attr.tag;
import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class MallFragment extends BaseFragment implements XListView.IXListViewListener {
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
    XListView lvMallProduct;
    private int index = 1;
    private GetProductResult getProductResult;
    private List<Product> list = new ArrayList();
    SharedPopupWindow sharedPopupWindow;
    private View view;
    private CommonAdapter<Product> productAdapter;
    private int maxIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_mall, null);
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        sharedPopupWindow = new SharedPopupWindow(getActivity());
        netLoad();
        initView();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            netLoad();
        }
    }

    private void netLoad() {
        Map map = new HashMap();
        map.put("index", index + "");
        map.put("pageSize", 10);

        VolleyRequestUtils.getInstance().getRequestPost(getActivity(), AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e("result--" + tag + "  ", result);

                try {
                    getProductResult = new Gson().fromJson(result, GetProductResult.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                if (Global.RESULT_CODE.equals(getProductResult.getCode())) {
                    maxIndex = getProductResult.getResult().getTotalPage();
                    List temp = getProductResult.getResult().getResult();
                    if (maxIndex >= index) {
                        list.addAll(temp);
                    } else {
                        showToastReal("已经是全部数据了");
                    }

                    if (list.size() > 0) {
                        initView();
                    }
                } else {
                    showToastReal(getProductResult.getMsg());
                }
                lvMallProduct.stopLoadMore();
                lvMallProduct.stopRefresh();
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                lvMallProduct.stopLoadMore();
                lvMallProduct.stopRefresh();
            }
        }, map);
    }

    private void initView() {
        idIgBack.setVisibility(View.GONE);
        titleText.setText("商城");

        lvMallProduct.setXListViewListener(this);
        lvMallProduct.setAutoLoadEnable(false);
        lvMallProduct.setPullRefreshEnable(false);
        lvMallProduct.setPullLoadEnable(true);

        setViewData();


    }

    private void setViewData() {
        if (productAdapter == null) {
            setAdapter();
        } else {
            productAdapter.notifyDataSetChanged();
        }
    }

    private void setAdapter() {
        productAdapter = new CommonAdapter<Product>(list, R.layout.item_product) {
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
                helper.setText(R.id.tv_item_tag, item.getTagName());
                helper.setText(R.id.tv_product_item_information, item.getIntroduction().replace(System.getProperty("line.separator"), " "));
                helper.setViewOnclick(R.id.iv_item_collection, new View.OnClickListener() {
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
                helper.setViewOnclick(R.id.iv_item_share, new View.OnClickListener() {
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
        lvMallProduct.setAdapter(productAdapter);
        lvMallProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", list.get(i));
                openActivity(ProductActivity.class, bundle);
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
        openActivity(SearchTeaActivity.class, null);
    }

    @Override
    public void onRefresh() {
        index = 1;
        list.clear();
        netLoad();
    }

    @Override
    public void onLoadMore() {
        index++;
        netLoad();
    }
}

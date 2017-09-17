package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.GetShopsResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.ShopsChoosePopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class ChooseShopActivity extends BaseActivity {
    @Bind(R.id.tv_login_cancle)
    TextView tvLoginCancle;
    @Bind(R.id.rl_choose_shop)
    RelativeLayout rlChooseShop;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.rl_root_view)
    LinearLayout rlRootView;
    private List<GetShopsResult.Shop> shops;
    private ShopsChoosePopupWindow shopPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        UIUtils.setBarTint(this, true);
        getShops();
    }

    private void getShops() {

        VolleyRequestUtils.getInstance().getRequestGet(this, AppURL.GET_SHOPS_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("url", AppURL.GET_SHOPS_LIST);
                L.e("result", result);
                GetShopsResult getShopsResult = new Gson().fromJson(result, GetShopsResult.class);
                if (Global.RESULT_CODE.equals(getShopsResult.getCode())) {
                    shops = getShopsResult.getResult();
                    if (shops != null && shops.size() > 0) {
                        initView();
//                        openActivity(PersonalDataActivity.class, null);
                    }
                }else {
                    showToastReal(getShopsResult.getMsg());
                }


            }

            @Override

            public void onFail(String fail) {
                L.e("fail", fail);

            }
        });
    }

    private void initView() {
        shopPopup = new ShopsChoosePopupWindow(this);
        rlChooseShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopPopup.showPop(rlRootView);
            }
        });


        shopPopup.setLvShop(shops, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvShopName.setText(shops.get(position).getShopName());
                shopPopup.closePopupWindow();
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(tvShopName.getText().toString())) {
                    openActivity(PersonalDataActivity.class, null);
                }
            }
        });
    }

    @OnClick(R.id.tv_login_cancle)
    public void onViewClicked() {
        finish();
    }
}

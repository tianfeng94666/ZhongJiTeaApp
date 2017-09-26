package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.ShopInfoResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ShopInformationActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.tv_shop_phone)
    TextView tvShopPhone;
    @Bind(R.id.tv_shop_time)
    TextView tvShopTime;
    private ShopInfoResult shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_information);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
        netload();
    }

    private void initView() {
      ShopInfoResult.ResultBean resultBean=shopInfo.getResult();
        titleText.setText("门店信息");
        tvShopName.setText(resultBean.getShopName());
        tvShopAddress.setText(resultBean.getAddress());
        tvShopPhone.setText(resultBean.getTel());
        tvShopTime.setText(resultBean.getBusiStartTime()+"--"+resultBean.getBusiEndTime());
    }

    public void netload() {
        Map map = new HashMap();
        map.put("shopId", Global.shopId);
        String url = AppURL.SHOPINFO_URL + "/" + Global.shopId;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                shopInfo = new Gson().fromJson(result, ShopInfoResult.class);
                if (Global.RESULT_CODE.equals(shopInfo.getCode())) {
                    if (shopInfo.getResult() != null) {
                        initView();
                    }

                } else {
                    showToastReal(shopInfo.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }
}

package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        UIUtils.setBarTint(this, true);
        getShops();
    }

    private void getShops() {
        Map map = new HashMap();
        map.put("", 0);
        map.put("",10);
        map.put("","");
        VolleyRequestUtils.getInstance().getRequestPost(this, AppURL.GET_PRODUCT_LIST, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);

                openActivity(PersonalDataActivity.class,null);
            }

            @Override

            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }
}

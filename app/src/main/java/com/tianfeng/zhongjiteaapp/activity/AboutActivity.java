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
import com.tianfeng.zhongjiteaapp.json.AboutResult;
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

public class AboutActivity extends BaseActivity {

    @Bind(R.id.iv_app_icon)
    ImageView ivAppIcon;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;
    @Bind(R.id.tv_descript)
    TextView tvDescript;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_app_phone)
    TextView tvAppPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
        initView();
        netLoad();
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("关于我们");
    }

    private void netLoad() {
        Map map = new HashMap();
        String url = AppURL.APPINFO_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                AboutResult aboutResult = new Gson().fromJson(result, AboutResult.class);
                if (Global.RESULT_CODE.equals(aboutResult.getCode())) {
                    if (aboutResult.getResult() != null) {
                        tvDescript.setText("关于小茶宝：" + aboutResult.getResult().getApp_introduction());
                        tvVersion.setText("版本号：" + aboutResult.getResult().getApp_android_version());
                        tvAppPhone.setText("客服电话：" + aboutResult.getResult().getCustomer_service_phone());
                    }

                } else {
                    showToastReal(aboutResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
            }
        }, map);
    }
}

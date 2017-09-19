package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.ShopInfoResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class MyCollectedActivity extends BaseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollect);
        ButterKnife.bind(this);
        netload();
        initView();
    }

    public void netload() {
        Map map = new HashMap();
        map.put("userId", Global.UserId);
        String url = AppURL.MY_COLLETED ;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
//                shopInfo = new Gson().fromJson(result, ShopInfoResult.class);
//                if (Global.RESULT_CODE.equals(shopInfo.getCode())) {
//                    if (shopInfo.getResult() != null) {
//                        initView();
//                    }
//
//                } else {
//                    showToastReal(shopInfo.getMsg());
//                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
            }
        }, map);
    }
    private void initView() {
        idIgBack.setVisibility(View.VISIBLE);
        titleText.setText("我的收藏");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("1");
        }
        lvMycollected.setAdapter(new CommonAdapter<String>(list, R.layout.item_product) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {

            }
        });
    }


}

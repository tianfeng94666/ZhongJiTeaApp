package com.tianfeng.zhongjiteaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.ItemHelpResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class TextActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    private String type;
    private String helpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
        getData(getIntent());
    }

    private void getData(Intent intent) {
        Bundle bundle = intent.getExtras();
        type = bundle.getString("type");
        //type 0-- 来自帮助协议 1-- 公告和注册协议
        if ("0".equals(type)) {
            helpId = bundle.getString("helpId");
            getHelp();
        }else if("1".equals(type)){
            titleText.setText(bundle.getString("title"));
            tvContent.setText(Html.fromHtml(bundle.getString("content")));
        }
    }

    private void getHelp() {
        Map map = new HashMap();
        String url = AppURL.ITEM_HELP_URL + "/" + helpId;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                ItemHelpResult helpResult = new Gson().fromJson(result, ItemHelpResult.class);
                if (Global.RESULT_CODE.equals(helpResult.getCode())) {
                    titleText.setText(helpResult.getResult().get(0).getTitle());
                    tvContent.setText(Html.fromHtml(helpResult.getResult().get(0).getContent()));

                } else {
                    showToastReal(helpResult.getMsg());
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

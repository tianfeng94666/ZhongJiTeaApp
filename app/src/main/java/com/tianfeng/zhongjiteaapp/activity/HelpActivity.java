package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.tianfeng.zhongjiteaapp.json.GetCodeResult;
import com.tianfeng.zhongjiteaapp.json.HelpResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class HelpActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_help)
    ListView lvHelp;
    private List<HelpResult.ResultBean> helpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
        netLoad();
    }

    private void netLoad() {
        Map map = new HashMap();
        String url = AppURL.HELP_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                HelpResult helpResult = new Gson().fromJson(result, HelpResult.class);
                if (Global.RESULT_CODE.equals(helpResult.getCode())) {
                    if (helpResult.getResult() != null) {
                        helpList = helpResult.getResult();
                        if (helpList.size() > 0) {
                            initView();
                        }
                    }

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

    private void initView() {
        idIgBack.setVisibility(View.VISIBLE);
        titleText.setText("使用帮助及协议");

        lvHelp.setAdapter(new CommonAdapter<HelpResult.ResultBean>(helpList, R.layout.item_help) {
            @Override
            public void convert(int position, BaseViewHolder helper, HelpResult.ResultBean item) {
                helper.setText(R.id.tv_help_name, helpList.get(position).getTypeName());
            }

        });
        lvHelp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("helpId",helpList.get(position).getTypeId());
                bundle.putString("type","0");
                openActivity(TextActivity.class,bundle);
            }
        });

    }

    public void onBack() {
        finish();
    }
}

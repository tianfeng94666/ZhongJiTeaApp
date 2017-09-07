package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        idIgBack.setVisibility(View.VISIBLE);
        final List<String> list = new ArrayList<>();

        list.add("小茶宝服务协议");
        list.add("仓储管理服务协议");
        list.add("仓储类问题");
        list.add("茶叶质押协议");
        list.add("茶叶赎回协议");

        lvHelp.setAdapter(new CommonAdapter<String>(list,R.layout.item_help) {
            @Override
            public void convert(int position, BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_help_name,list.get(position));
            }
        });


    }

    public void onBack() {
        finish();
    }
}

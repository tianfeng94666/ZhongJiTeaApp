package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class SearchTeaActivity extends BaseActivity {
    @Bind(R.id.et_search_tea)
    EditText etSearchTea;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.lv_seach_tea)
    ListView lvSeachTea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tea);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_cancle)
    public void onClick() {
        finish();
    }
}

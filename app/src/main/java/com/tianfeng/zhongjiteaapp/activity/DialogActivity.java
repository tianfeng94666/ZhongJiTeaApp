package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DialogActivity extends BaseActivity {
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        tvMessage.setText(getIntent().getExtras().get("key").toString());
    }

    @OnClick(R.id.tv_confirm)
    public void onClick() {
        finish();
    }
}

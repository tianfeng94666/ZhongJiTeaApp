package com.tianfeng.zhongjiteaapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class FirstActivity extends BaseActivity {


    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.iv_into)
    ImageView ivInto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        UIUtils.setBarTint(this);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ivInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(LoginAcitivity.class, null);
            }
        });
    }





}

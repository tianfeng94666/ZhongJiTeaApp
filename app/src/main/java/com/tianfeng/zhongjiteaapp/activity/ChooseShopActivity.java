package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class ChooseShopActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        UIUtils.setBarTint(this,true);
    }
}

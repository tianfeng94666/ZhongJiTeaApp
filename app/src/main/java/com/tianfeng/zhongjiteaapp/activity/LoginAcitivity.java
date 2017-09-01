package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class LoginAcitivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UIUtils.barTint(this,R.color.white);
    }
}

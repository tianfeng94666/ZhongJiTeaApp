package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

/**
 * Created by 田丰 on 2017/9/4.
 */

public class PersonalDataActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        UIUtils.setBarTint(this);
    }
}

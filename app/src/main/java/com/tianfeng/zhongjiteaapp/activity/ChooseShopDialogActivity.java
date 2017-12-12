package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class ChooseShopDialogActivity extends BaseActivity {
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_message1)
    TextView tvMessage1;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    GoToNextInterface goToNextInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_shop_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_confirm, R.id.tv_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                openActivity(ChooseShopActivity.class,null);
                finish();
                break;
            case R.id.tv_cancle:
                finish();
                break;
        }
    }

    interface  GoToNextInterface{
        void goToNext();
    }
}

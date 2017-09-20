package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.json.OrderBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 田丰 on 2017/9/11.
 */

public class PledgeActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.et_amount)
    EditText etAmount;
    @Bind(R.id.et_old_money)
    EditText etOldMoney;
    @Bind(R.id.et_evaluate_money)
    EditText etEvaluateMoney;
    @Bind(R.id.ll_money)
    LinearLayout llMoney;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    private OrderBean item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        item = (OrderBean) bundle.get("storageItem");
        if (item != null) {
            initView();
        }
    }

    private void initView() {
        int state= Integer.parseInt(item.getTransStatus());
        switch (state){
            //已暂存
            case 8:
                break;
            //

        }
    }
}

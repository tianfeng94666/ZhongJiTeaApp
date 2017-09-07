package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.viewutils.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class StorageTradeActivity extends BaseActivity {


    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.iv_item_product)
    CircleImageView ivItemProduct;
    @Bind(R.id.tv_item_name)
    TextView tvItemName;
    @Bind(R.id.iv_item_state)
    TextView ivItemState;
    @Bind(R.id.ll_)
    RelativeLayout ll;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_shipside_delivery)
    TextView tvShipsideDelivery;
    @Bind(R.id.tv_temporary_storage)
    TextView tvTemporaryStorage;
    @Bind(R.id.tv_make_over)
    TextView tvMakeOver;
    @Bind(R.id.tv_pledge)
    TextView tvPledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_trade);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_shipside_delivery, R.id.tv_temporary_storage, R.id.tv_make_over, R.id.tv_pledge})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_shipside_delivery:
                bundle.putString("key", "请与客服联系仓库线下提货");
                openActivity(DialogActivity.class, bundle);
                break;
            case R.id.tv_temporary_storage:
                bundle.putString("key", "请与客服联系仓库线下续存");
                openActivity(DialogActivity.class, bundle);
                break;
            case R.id.tv_make_over:
                bundle.putString("key", "请与客服联系买家");
                openActivity(DialogActivity.class, bundle);
                break;
            case R.id.tv_pledge:

                break;
        }
    }


}

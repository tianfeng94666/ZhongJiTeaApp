package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.net.ImageLoadOptions;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
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
    @Bind(R.id.tv_item_price)
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
    @Bind(R.id.tv_item_tag)
    TextView tvItemTag;
    @Bind(R.id.tv_item_type)
    TextView tvItemType;
    private OrderBean item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_trade);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
        geData();

    }

    private void initView() {
        titleText.setText(item.getGoodsName());
        tvItemName.setText(item.getGoodsName());
        tvItemTag.setText(item.getTagName());
        tvItemType.setText(item.getDeportName()+item.getTypeName());
        if (StringUtils.isEmpty(item.getTagName())) {
            tvItemTag.setVisibility(View.GONE);
        } else {
            tvItemTag.setVisibility(View.VISIBLE);
        }
        tvPrice.setText( "茶叶单价：" + item.getPrice()+"/"+item.getUnitName());
        tvAmount.setText("成交量：" + item.getQuantity());
        tvTotalMoney.setText("成交总金额：" + item.getTotal());
        tvDate.setText("购买时间：" + CommMethod.getFormatedDateTime(item.getCreateTime()));
        ivItemState.setText(CommMethod.getState(item.getTransStatus()));
        ImageLoader.getInstance().displayImage(AppURL.baseHost + "/" + item.getImgUrl(),ivItemProduct, ImageLoadOptions.getOptionsHight());
    }

    private void geData() {
        Bundle bundle = getIntent().getExtras();
        item = (OrderBean) bundle.getSerializable("storageItem");
        if(item!=null){
            initView();
        }
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
                bundle.putSerializable("storageItem",item);
                openActivity(PledgeActivity.class,bundle);
                break;
        }
    }


}

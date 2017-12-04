package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.dialog.ProtocolDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class ChangeActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_lable)
    TextView tvLable;
    @Bind(R.id.tv_lable2)
    TextView tvLable2;
    @Bind(R.id.et_price_low)
    EditText etPriceLow;
    @Bind(R.id.et_price_high)
    EditText etPriceHigh;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    private ProtocolDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        titleText.setText("输入转让信息");
    }
    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        goToNext();
    }
    private void goToNext() {
        View view = View.inflate(this,R.layout.dialog_protocol,null);
        dialog = new ProtocolDialog(this,view,R.style.protocol_dialog_theme);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvConfirm = (TextView)view.findViewById(R.id.tv_confirm);
        tvTitle.setText("XX协议");
        tvContent.setText("有没有");
        tvConfirm.setText("同意并继续");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastReal("okok");
            }
        });
//        dialog.setCancelable(false);
        dialog.show();
    }
}

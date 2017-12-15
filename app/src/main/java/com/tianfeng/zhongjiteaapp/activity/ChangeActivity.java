package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.dialog.ProtocolDialog;
import com.tianfeng.zhongjiteaapp.json.LoginProtocolResutl;
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.SimpleRequestResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Bind(R.id.et_amount)
    EditText etAmount;
    @Bind(R.id.tv_lable2)
    TextView tvLable2;
    @Bind(R.id.et_price_low)
    EditText etPriceLow;
    @Bind(R.id.et_price_high)
    EditText etPriceHigh;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    private ProtocolDialog dialog;
    private OrderBean item;
    private List<LoginProtocolResutl.ResultBean> helpList;
    static BaseActivity instance;
    private String orderStatus;
    private String orderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        instance=this;
        getDate();
        initView();
    }

    private void getDate() {
        Bundle bundle = getIntent().getExtras();
        item = (OrderBean) bundle.getSerializable("storageItem");
    }

    private void initView() {
       orderStatus = item.getTransStatus();
        orderType = item.getTransType();
        if(Global.CHANGE.equals(orderType)&&Global.SHENHE_WAIT.equals(orderStatus)){
            String expectation =item.getExpectation();
            String[] prices = expectation.split("~");
            etPriceLow.setText(prices[0]);
            etPriceHigh.setText(prices[1]);
            etAmount.setText(item.getQuantity());
            setEditText(etAmount,false);
            setEditText(etPriceLow,false);
            setEditText(etPriceHigh,false);
            tvConfirm.setText("取消转让");
        }else {
            setEditText(etAmount,true);
            setEditText(etPriceLow,true);
            setEditText(etPriceHigh,true);
            titleText.setText("输入转让信息");
            tvConfirm.setText("确定");
        }
    }

    public  void setEditText(EditText editText,boolean iscan){
        editText.setFocusable(iscan);
        editText.setFocusableInTouchMode(iscan);
    }
    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        if(Global.CHANGE.equals(orderType)&&Global.SHENHE_WAIT.equals(orderStatus)){
            cancle();
        }else {
            goToNext();
        }

    }

    private void cancle() {
        Map map = new HashMap();
        map.put("id", item.getId());

        String url = AppURL.CHANGE_CANCLE_REQUEST;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                SimpleRequestResult getData = new Gson().fromJson(result, SimpleRequestResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", getData.getResult());
                    openActivity(DialogActivity.class, bundle);

                } else {
                    showToastReal(getData.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }

    private void goToNext() {
        if (StringUtils.isEmpty(etAmount.getText().toString())) {
            showToastReal("请输入数量");
            return;
        }


        if (StringUtils.isEmpty(etPriceHigh.getText().toString())) {
            showToastReal("请输入最高价格");
            return;
        }
        if (StringUtils.isEmpty(etPriceLow.getText().toString())) {
            showToastReal("请输入最低价格");
            return;
        }
        try {

            double high = Double.parseDouble(etPriceHigh.getText().toString());
            double low = Double.parseDouble(etPriceLow.getText().toString());
            if(low>high){
                showToastReal("请输入最低价格不能大于最高价格");
                return;
            }
            double amount = Double.parseDouble(etAmount.getText().toString());
            if(amount==0&&amount>Double.parseDouble(item.getQuantity())){
                showToastReal("数量不能为0且不能大于暂存数量");
                return;
            }
        } catch (NumberFormatException e) {
            showToastReal("输入数字错误");
        }
        getProtocol();
    }

    private void changeRequest() {
        Map map = new HashMap();
        map.put("id", item.getId());
        map.put("expectation", etPriceLow.getText().toString()+"~"+etPriceHigh.getText().toString());
        map.put("quantity", etAmount.getText().toString());
        String url = AppURL.CHANGE_REQUEST;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                SimpleRequestResult getData = new Gson().fromJson(result, SimpleRequestResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", getData.getResult());
                    openActivity(DialogActivity.class, bundle);

                } else {
                    showToastReal(getData.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }

    public void getProtocol() {
        Map map = new HashMap();
        String url = AppURL.GET_PROTOCOL_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                LoginProtocolResutl loginResult = new Gson().fromJson(result, LoginProtocolResutl.class);
                if (Global.RESULT_CODE.equals(loginResult.getCode())) {
                    if (loginResult.getResult() != null) {
                        helpList = loginResult.getResult();
                        if (helpList.size() > 0) {
                            showComfirmDialog();
                        }
                    }

                } else {
                    showToastReal(loginResult.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }

    private void showComfirmDialog() {
        View view = View.inflate(this, R.layout.dialog_protocol, null);
        dialog = new ProtocolDialog(this, view, R.style.protocol_dialog_theme);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvTitle.setText(helpList.get(1).getTitle());
        tvContent.setText(Html.fromHtml(helpList.get(1).getContent()));
        tvConfirm.setText("同意并继续");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeRequest();
            }
        });
//        dialog.setCancelable(false);
        dialog.show();
    }
}

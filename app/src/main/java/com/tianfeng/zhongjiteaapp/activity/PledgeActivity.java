package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.tianfeng.zhongjiteaapp.json.PledgeResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Bind(R.id.tv_old_money)
    TextView tvOldMoney;
    @Bind(R.id.tv_evaluate_money)
    TextView tvEvaluateMoney;
    @Bind(R.id.ll_money)
    LinearLayout llMoney;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.ll_old_money)
    LinearLayout llOldMoney;
    @Bind(R.id.ll_real_money)
    LinearLayout llRealMoney;
    @Bind(R.id.tv_reback_money)
    TextView tvRebackMoney;
    @Bind(R.id.ll_reback_money)
    LinearLayout llRebackMoney;
    private OrderBean item;
    static BaseActivity instance;
    private List<LoginProtocolResutl.ResultBean> helpList;
    private ProtocolDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pledge);
        ButterKnife.bind(this);
        UIUtils.setBarTint(this, false);
        instance = this;
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
        String state = item.getTransStatus();
        String orderState = item.getTransType();
        //  确认质押（0004,1）申请赎回（0004,3）确认赎回（0002,1）申请质押（0001,3）
        if (orderState.equals(Global.PLEDGE) && state.equals("1")) {
            init1();
        } else if (orderState.equals(Global.PLEDGE) && state.equals("3")) {
            init3();
        } else if (orderState.equals(Global.REBACK) && state.equals("1")) {
            init6();
        } else if (orderState.equals(Global.STORAGE) && state.equals("3")) {
            init8();
        } else {
            init0();
        }

    }

    public void setTextViewData() {
        etAmount.setText(item.getQuantity() + "");
        tvOldMoney.setText(item.getTotal());
        tvEvaluateMoney.setText(item.getAssessment());
        tvRebackMoney.setText(item.getRedeem());
    }

    private void init6() {
        tvConfirm.setText("确认赎回");
        tvCancle.setText("取消赎回");
        titleText.setText("确认赎回");
        etAmount.setEnabled(false);
        setTextViewData();
        tvConfirm.setVisibility(View.VISIBLE);
        tvCancle.setVisibility(View.VISIBLE);
        llMoney.setVisibility(View.VISIBLE);
        llRebackMoney.setVisibility(View.VISIBLE);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmReback();
            }
        });

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleReback();
            }
        });
    }

    private void cancleReback() {
        Map map = new HashMap();

        map.put("id", item.getId());
        String url = AppURL.RETURN_CANCLE;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
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

    private void confirmReback() {
        Map map = new HashMap();

        map.put("id", item.getId());
        String url = AppURL.RETURN_CONFIRM;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "已提交申请，等待后台审核");
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

    private void init3() {
        tvConfirm.setText("申请赎回");
        titleText.setText("已质押茶叶");
        etAmount.setEnabled(false);
        setTextViewData();
        tvConfirm.setVisibility(View.VISIBLE);
        tvCancle.setVisibility(View.GONE);
        llMoney.setVisibility(View.VISIBLE);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reBack();
            }
        });
    }

    private void reBack() {
        Map map = new HashMap();

        map.put("id", item.getId());
        String url = AppURL.RETURN_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "已提交申请，等待后台审核");
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

    private void init1() {
        tvConfirm.setText("确认质押");
        tvCancle.setText("取消质押");
        titleText.setText("确认评估金额");
        etAmount.setEnabled(false);
        setTextViewData();
        tvConfirm.setVisibility(View.VISIBLE);
        tvCancle.setVisibility(View.VISIBLE);
        llMoney.setVisibility(View.VISIBLE);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmPledge();
            }
        });

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canclePledge();
            }
        });
    }

    private void canclePledge() {
        Map map = new HashMap();

        map.put("id", item.getId());
        String url = AppURL.PLEDGE_CANCLE_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "已提交申请，等待后台审核");
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

    private void confirmPledge() {
        Map map = new HashMap();

        map.put("id", item.getId());
        String url = AppURL.PLEDGE_CONFIRM_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "已提交申请，等待后台审核");
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


    private void init0() {
        Bundle bundle = new Bundle();
        bundle.putString("key", "已提交申请，等待后台审核");
        openActivity(DialogActivity.class, bundle);
    }

    /**
     * 暂存，可提交质押
     */
    private void init8() {
        titleText.setText("选择质押数量");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProtocol();
            }
        });
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
                pledge();
            }
        });
//        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 进行质押申请
     */
    private void pledge() {
        if (StringUtils.isEmpty(etAmount.getText().toString())) {
            showToastReal("请填写数量！");
            return;
        }
        double amount = Double.parseDouble(etAmount.getText().toString());
        double maxamount = Double.parseDouble(item.getQuantity());
        if (amount <= 0) {
            showToastReal("数量不能为负数或0");
            return;
        }
        if (amount > maxamount) {
            showToastReal("数量不能超过质押数量");
            return;
        }
        Map map = new HashMap();
//        map.put("goodsId", item.getGoodsId());
        map.put("quantity", etAmount.getText().toString());
        map.put("id", item.getId());
        String url = AppURL.PLEDGE_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "已提交申请，等待后台审核");
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

}

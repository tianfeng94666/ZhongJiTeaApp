package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
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
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.PledgeResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
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
    private OrderBean item;
    static BaseActivity instance;


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
        int state = Integer.parseInt(item.getTransStatus());
        switch (state) {
            //可以进行确认或取消
            case 1:
                init1();
                break;
            //可以进行赎回
            case 3:
                init3();
                break;
            //进行赎回确认或取消
            case 6:
                init6();
                break;
            //已暂存
            case 8:
                init8();
                break;

        }
    }

    private void init6() {
        tvConfirm.setText("确认赎回");
        tvCancle.setText("取消赎回");
        tvConfirm.setVisibility(View.VISIBLE);
        tvCancle.setVisibility(View.VISIBLE);
        llMoney.setVisibility(View.VISIBLE);
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
                    bundle.putString("key", "已提交申请，等待后台审核");
                    openActivity(DialogActivity.class, bundle);

                } else {
                    showToastReal(getData.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
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
                showToastReal(fail);
            }
        }, map);
    }

    private void init3() {
        tvConfirm.setText("申请赎回");
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
        VolleyRequestUtils.getInstance().getRequestPost(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
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
                showToastReal(fail);
            }
        }, map);
    }

    private void init1() {
        tvConfirm.setText("确认质押");
        tvCancle.setText("取消质押");
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
                showToastReal(fail);
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
                showToastReal(fail);
            }
        }, map);
    }

    //质押待审核
    private void init0() {
        Bundle bundle = new Bundle();
        bundle.putString("key", "已提交申请，等待后台审核");
        openActivity(DialogActivity.class, bundle);
    }

    /**
     * 暂存，可提交质押
     */
    private void init8() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pledge();
            }
        });
    }

    /**
     * 进行质押申请
     */
    private void pledge() {
        if (StringUtils.isEmpty(etAmount.getText().toString())) {
            showToastReal("请填写数量！");
            return;
        }
        Map map = new HashMap();
        map.put("goodsId", item.getGoodsId());
        map.put("quantity", etAmount.getText().toString());
        map.put("id", item.getId());
        String url = AppURL.PLEDGE_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getRequestPost(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
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
                showToastReal(fail);
            }
        }, map);

    }

}

package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
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
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.PledgeResult;
import com.tianfeng.zhongjiteaapp.json.SimpleRequestResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class XianTiActivity extends BaseActivity {
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
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.et_amount)
    EditText etAmount;
    private OrderBean item;
    static BaseActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xianti);
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
        titleText.setText("输入现提数量");
    }

    @OnClick({R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_confirm:
                comfirm();
                break;
        }
    }

    private void comfirm() {
        if (StringUtils.isEmpty(etAmount.getText().toString())){
            showToastReal("请输入数量");
            return;
        }
        Map map = new HashMap();
        map.put("id", item.getId());
        map.put("quantity",etAmount.getText().toString());
        String url = AppURL.XIANTI_REQUEST;
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
}

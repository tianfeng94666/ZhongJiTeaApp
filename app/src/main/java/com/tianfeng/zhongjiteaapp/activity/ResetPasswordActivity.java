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
import com.tianfeng.zhongjiteaapp.json.GetCodeResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CountTimerButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 田丰 on 2017/9/18.
 */

public class ResetPasswordActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.et_phonenumber)
    EditText etPhonenumber;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_login_code)
    EditText etLoginCode;
    @Bind(R.id.ll_code)
    LinearLayout llCode;
    @Bind(R.id.tv_login_code)
    TextView tvLoginCode;
    @Bind(R.id.et_reset)
    EditText etReset;
    @Bind(R.id.et_password_confirm)
    EditText etPasswordConfirm;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    private CountTimerButton mCountDownTimerUtils;
    private String bizId;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reste_password);
        UIUtils.setBarTint(this, false);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleText.setText("重置密码");
        phone = SpUtils.getInstace(this).getString("phoneNumber");
        etPhonenumber.setText(phone);
    }


    private void resetPassword() {
        String bizIdEncode = null;
        try {
            bizIdEncode = URLEncoder.encode(Global.BIZID, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String phoneNumber = etPhonenumber.getText().toString();
        String url = AppURL.REGISTER_URL + "/" + bizIdEncode + "/" + etLoginCode.getText().toString();
        L.e("url=", url);
        Map map = new HashMap();
        map.put("phoneNumber", phoneNumber);
        map.put("bizId", Global.BIZID);
        map.put("code",etLoginCode.getText().toString());
        map.put("password", etReset.getText().toString());
        map.put("rePassword", etPasswordConfirm.getText().toString());
        L.e("map=", map.toString());
        VolleyRequestUtils.getInstance().getRequestPost(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                showToastReal(fail);
            }
        }, map);
    }

    private void getLoginCode() {


        mCountDownTimerUtils = new CountTimerButton(tvLoginCode, 60000, 1000);
        tvLoginCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimerUtils.start();
                getCode();
            }
        });


    }

    @OnClick({R.id.tv_login_code, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_code:
                getLoginCode();
                break;
            case R.id.tv_confirm:
                resetPassword();
                break;
        }
    }

    private void getCode() {
        Map map = new HashMap();
        map.put("phoneNumber", etPhonenumber.getText().toString());
        String url = AppURL.GET_MESSAGE_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                GetCodeResult getCodeResult = new Gson().fromJson(result, GetCodeResult.class);
                if (Global.RESULT_CODE.equals(getCodeResult.getCode())) {
//                    Global.JESSIONID = getCodeResult.getJsessionid();
                    bizId = getCodeResult.getResult().getBizId();
                    Global.BIZID = bizId;
                } else {
                    showToastReal(getCodeResult.getMsg());
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

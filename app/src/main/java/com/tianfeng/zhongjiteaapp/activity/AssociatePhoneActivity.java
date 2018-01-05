package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.GetCodeResult;
import com.tianfeng.zhongjiteaapp.json.LoginProtocolResutl;
import com.tianfeng.zhongjiteaapp.json.LoginResult;
import com.tianfeng.zhongjiteaapp.json.MessageCheckResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CountTimerButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 田丰 on 2017/9/4.
 */

public class AssociatePhoneActivity extends BaseActivity {
    @Bind(R.id.tv_login_cancle)
    TextView tvLoginCancle;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_login_code)
    EditText etLoginCode;
    @Bind(R.id.ll_code)
    LinearLayout llCode;
    @Bind(R.id.tv_login_code)
    TextView tvLoginCode;
    @Bind(R.id.cb_ischeck)
    CheckBox cbIscheck;
    @Bind(R.id.tv_agreement1)
    TextView tvAgreement1;
    @Bind(R.id.tv_agreement2)
    TextView tvAgreement2;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.ll_register)
    LinearLayout llRegister;
    private CountTimerButton mCountDownTimerUtils;
    private String bizId;
    private List<LoginProtocolResutl.ResultBean> helpList;
    private LoginResult loginResult;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.setBarTint(this,true);
        setContentView(R.layout.activity_associate_phone);
        ButterKnife.bind(this);
        getDate();
        netLoad();
        initView();
    }

    private void getDate() {
        Bundle bundle =getIntent().getExtras();
        type  =bundle.getInt("type");
    }

    private void initView() {
        /**
         * 将登陆模式清空
         */
        SpUtils.getInstace(this).saveInt("loginType", 0);
        String phone = SpUtils.getInstace(this).getString("phoneNumber");
        if (!StringUtils.isEmpty(phone)) {
            etPhone.setText(phone);
        }
    }

    @OnClick({R.id.tv_login_cancle, R.id.tv_login_code, R.id.tv_agreement1, R.id.tv_agreement2, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_cancle:
                finish();
                break;
            case R.id.tv_login_code:
                getLoginCode();
                break;
            case R.id.cb_ischeck:
                break;
            case R.id.tv_agreement1:
                gotoProtocol(0);
                break;
            case R.id.tv_agreement2:
                gotoProtocol(1);
                break;
            case R.id.tv_next:
                goNext(type);
                break;
        }
    }

    private void goNext(final int type) {

        if (cbIscheck.isChecked()) {
            if (StringUtils.isEmpty(etLoginCode.getText().toString())) {
                showToastReal("请输入验证码");
                return;
            }else {
                Global.CODE = etLoginCode.getText().toString();
            }

            if (StringUtils.isEmpty(etPhone.getText().toString())) {
                showToastReal("请输入手机号");
                return;
            }
            if (StringUtils.isEmpty(Global.BIZID)) {
                showToastReal("未获取验证码");
                return;
            }
            String bizIdEncode = null;
            try {
                bizIdEncode = URLEncoder.encode(Global.BIZID, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            final String phoneNumber = etPhone.getText().toString();
            String url = AppURL.ASSOCIATE_PHONE + "/" + bizIdEncode + "/" + Global.CODE;

            final Map map = new HashMap();
            map.put("mobile", phoneNumber);
            map.put("bizId", Global.BIZID);
            map.put("code", Global.CODE);;
            map.put("nickName", Global.nickName);
            if(type ==0){
                map.put("wx", Global.WX_OPENID);
            }else {
                map.put("qq", Global.QQ_OPENID);
            }
            if(!StringUtils.isEmpty(Global.HeadView)){
                map.put("imgUrl",Global.HeadView);
            }
            L.e(url);
            VolleyRequestUtils.getInstance().getRequestPost(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
                @Override
                public void onSuccess(String result) {
                    L.e("result", result);
                    loginResult = new Gson().fromJson(result, LoginResult.class);
                    if (Global.RESULT_CODE.equals(loginResult.getCode())) {
                        Global.UserId = loginResult.getResult().getId();
                        Global.JESSIONID = loginResult.getJsessionid();
                        Global.HeadView = AppURL.baseHost + loginResult.getResult().getImgUrl();
                        Global.shopId = loginResult.getResult().getShopId();
                        Global.QQ_OPENID = loginResult.getResult().getQq();
                        Global.WX_OPENID = loginResult.getResult().getWx();
                        Global.isLogin = true;
                        SpUtils.getInstace(AssociatePhoneActivity.this).saveBoolean("isExit", false);
                        SpUtils.getInstace(AssociatePhoneActivity.this).saveString("phoneNumber",phoneNumber);
                        if(type ==0){
                            SpUtils.getInstace(AssociatePhoneActivity.this).saveInt("loginType", 1);
                        }else {
                            SpUtils.getInstace(AssociatePhoneActivity.this).saveInt("loginType", 2);
                        }
                        openActivity(MainActivity.class, null);
                        finish();
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


        } else {
            showToastReal("请勾选是否同意协议");
        }

    }

    private void getLoginCode() {
        if (UIUtils.isMobileNO(etPhone.getText().toString())) {
            mCountDownTimerUtils = new CountTimerButton(tvLoginCode, 60000, 1000);
            tvLoginCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCountDownTimerUtils.start();
                    getCode();
                }
            });

        } else {
            showToastReal("手机号码输入错误！");
        }
    }

    public void gotoProtocol(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("type", "1");
        if (helpList != null && helpList.size() > 0) {
            bundle.putString("title", helpList.get(i).getTitle());
            bundle.putString("content", helpList.get(i).getContent());
            openActivity(TextActivity.class, bundle);
        }
    }

    private void netLoad() {
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
    private void getCode() {
        Map map = new HashMap();
        map.put("phoneNumber", etPhone.getText().toString());
        String url = AppURL.GET_MESSAGE_REGISTER_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                GetCodeResult getCodeResult = new Gson().fromJson(result, GetCodeResult.class);
                if (Global.RESULT_CODE.equals(getCodeResult.getCode())) {
                    bizId = getCodeResult.getResult().getBizId();
                    Global.BIZID = bizId;
                } else {
                    showToastReal(getCodeResult.getMsg());
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

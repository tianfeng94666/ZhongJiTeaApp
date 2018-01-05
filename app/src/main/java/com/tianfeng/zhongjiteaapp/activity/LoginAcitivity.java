package com.tianfeng.zhongjiteaapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.GetCodeResult;
import com.tianfeng.zhongjiteaapp.json.LoginProtocolResutl;
import com.tianfeng.zhongjiteaapp.json.LoginResult;
import com.tianfeng.zhongjiteaapp.json.MessageCheckResult;
import com.tianfeng.zhongjiteaapp.json.QQCheckDateResult;
import com.tianfeng.zhongjiteaapp.json.QQGetTokenResult;
import com.tianfeng.zhongjiteaapp.json.QQUserData;
import com.tianfeng.zhongjiteaapp.json.WXDate;
import com.tianfeng.zhongjiteaapp.json.WXLoginCheckResult;
import com.tianfeng.zhongjiteaapp.json.WeixinResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.CountTimerButton;
import com.tianfeng.zhongjiteaapp.wxapi.WXEntryActivity;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class LoginAcitivity extends BaseActivity {


    @Bind(R.id.tv_login_cancle)
    TextView tvLoginCancle;
    @Bind(R.id.tv_login_tv)
    TextView tvLoginTv;
    @Bind(R.id.tv_regisit_tv)
    TextView tvRegisitTv;
    @Bind(R.id.et_phone)
    EditText etLoginPhone;
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
    @Bind(R.id.et_login_password)
    EditText etLoginPassword;
    @Bind(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.ll_login)
    LinearLayout llLogin;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    private CountTimerButton mCountDownTimerUtils;
    private String bizId;//验证码请求返回的bizId
    private List<LoginProtocolResutl.ResultBean> helpList;
    private LoginResult loginResult;
    private IWXAPI mWxApi;
    private Tencent mTencent;
    private QQCallback qqCallback;
    private IUiListener userInfoListener; //获取用户信息监听器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.setBarTint(this, true);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        registTo();
        initView();
        netLoad();

    }


    private void initView() {

//        tvNext.setClickable(false);
        String phone = SpUtils.getInstace(this).getString("phoneNumber");
        String password = SpUtils.getInstace(this).getString("password");
        if (!StringUtils.isEmpty(phone)) {
            etLoginPhone.setText(phone);
        }
        if (!StringUtils.isEmpty(password)) {
            etLoginPassword.setText(password);
        }
        boolean isExit = SpUtils.getInstace(this).getBoolean("isExit", true);
        if (!isExit) {
            login();
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

    @OnClick({R.id.tv_login_cancle, R.id.tv_login_tv, R.id.tv_regisit_tv, R.id.tv_login_code, R.id.cb_ischeck, R.id.tv_agreement1, R.id.tv_agreement2, R.id.tv_next, R.id.tv_login_forget_password, R.id.tv_login, R.id.iv_weixin, R.id.iv_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_cancle:
                Bundle bundle = new Bundle();
                bundle.putInt("openType", 1);
                openActivity(MainActivity.class, bundle);
                break;
            case R.id.tv_login_tv:
                showLogin();
                break;
            case R.id.tv_regisit_tv:
                showRegisit();
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
                goNext();
                break;
            case R.id.tv_login_forget_password:
                openActivity(ResetPasswordActivity.class, null);
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.iv_weixin:
                weiChatLogin();
                break;
            case R.id.iv_qq:
                qqLogin();
                break;
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
                        if (helpList.size() > 0) {
                            initView();
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

    private void goNext() {

        if (cbIscheck.isChecked()) {
            if (StringUtils.isEmpty(etLoginPhone.getText().toString())) {
                showToastReal("请输入手机号");
                return;
            }
            if (StringUtils.isEmpty(etLoginCode.getText().toString())) {
                showToastReal("请输入验证码");
                return;
            }


            if (StringUtils.isEmpty(etRegisterPassword.getText().toString())) {
                showToastReal("请输入密码");
                return;
            }

            String url = AppURL.MESSAGE_CHECK;
            Map map = new HashMap();
            map.put("phoneNumber", etLoginPhone.getText().toString());
            map.put("bizId", bizId);
            map.put("code", etLoginCode.getText().toString());
            VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
                @Override
                public void onSuccess(String result) {
                    L.e("result", result);
                    MessageCheckResult messageCheckResult = new Gson().fromJson(result, MessageCheckResult.class);
                    if (Global.RESULT_CODE.equals(messageCheckResult.getCode())) {
                        SpUtils.getInstace(LoginAcitivity.this).saveString("phoneNumber", etLoginPhone.getText().toString());
                        Global.PhoneNumber = etLoginPhone.getText().toString();
                        Global.CODE = etLoginCode.getText().toString();
                        regisit();
                    } else {
                        showToastReal(messageCheckResult.getMsg());
                    }
                }

                @Override
                public void onFail(String fail) {
                    L.e("fail", fail);
//                    showToastReal(fail);
                }
            }, map);

        } else {
            showToastReal("请勾选是否同意协议");
        }
//        //调试使用
//        openActivity(ChooseShopActivity.class, null);
    }

    private void regisit() {
        String bizIdEncode = null;
        try {
            bizIdEncode = URLEncoder.encode(Global.BIZID, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String phoneNumber = SpUtils.getInstace(this).getString("phoneNumber");
        String url = AppURL.REGISTER_URL + "/" + bizIdEncode + "/" + Global.CODE;

        Map map = new HashMap();
        map.put("mobile", phoneNumber);
        map.put("bizId", Global.BIZID);
        map.put("code", Global.CODE);
        map.put("loginName", phoneNumber);
        map.put("nickName", phoneNumber);
        map.put("password", etRegisterPassword.getText().toString());

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
                    Global.isLogin = true;
                    SpUtils.getInstace(LoginAcitivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(LoginAcitivity.this).saveInt("loginType", 3);
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


    }

    private void login() {
        tvLogin.setClickable(false);
        if (etLoginPhone.getText().toString().isEmpty()) {
            showToastReal("手机号不能为空！");
            return;
        }
        if (etLoginPassword.getText().toString().isEmpty()) {
            showToastReal("密码不能为空！");
            return;
        }
        Map map = new HashMap();
        map.put("loginName", etLoginPhone.getText().toString());
        map.put("password", etLoginPassword.getText().toString());
        VolleyRequestUtils.getInstance().getStringPostRequest(this, AppURL.LOGIN_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                tvLogin.setClickable(true);
                L.e("result", result);
                LoginResult loginResult = new Gson().fromJson(result, LoginResult.class);
                if (Global.RESULT_CODE.equals(loginResult.getCode())) {
                    Global.JESSIONID = loginResult.getJsessionid();
                    Global.UserId = loginResult.getResult().getId();
                    Global.shopId = loginResult.getResult().getShopId();
                    Global.HeadView = AppURL.baseHost + loginResult.getResult().getImgUrl();
                    Global.nickName = loginResult.getResult().getNickName();
                    Global.isLogin = true;
                    SpUtils.getInstace(LoginAcitivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(LoginAcitivity.this).saveString("phoneNumber", etLoginPhone.getText().toString());
                    SpUtils.getInstace(LoginAcitivity.this).saveString("password", etLoginPassword.getText().toString());
                    SpUtils.getInstace(LoginAcitivity.this).saveInt("loginType", 3);
                    Bundle bundle = new Bundle();
                    bundle.putInt("openType", 0);
                    openActivity(MainActivity.class, bundle);
                } else {
                    showToastReal(loginResult.getMsg());
                }
//                Global.UserId =

            }

            @Override
            public void onFail(String fail) {
                tvLogin.setClickable(true);
                L.e("fail", fail);

            }
        }, map);
    }

    private void getLoginCode() {
        if (UIUtils.isMobileNO(etLoginPhone.getText().toString())) {
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

    private void getCode() {
        Map map = new HashMap();
        map.put("phoneNumber", etLoginPhone.getText().toString());
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

    private void showRegisit() {
        tvRegisitTv.setTextColor(getResources().getColor(R.color.oregon));
        tvLoginTv.setTextColor(getResources().getColor(R.color.text_color));
        llLogin.setVisibility(View.GONE);
        llRegister.setVisibility(View.VISIBLE);
    }

    private void showLogin() {

        tvRegisitTv.setTextColor(getResources().getColor(R.color.text_color));
        tvLoginTv.setTextColor(getResources().getColor(R.color.oregon));
        llLogin.setVisibility(View.VISIBLE);
        llRegister.setVisibility(View.GONE);
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastManager.showToastReal("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
        }
        return true;
    }

    private void qqLogin() {
        if (qqCallback == null) {
            qqCallback = new QQCallback();
        }

        mTencent.login(this, "all", qqCallback);
        baseShowWatLoading();
    }

    private void weiChatLogin() {

        if (!mWxApi.isWXAppInstalled()) {
            showToastReal("您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";

        mWxApi.sendReq(req);
        baseShowWatLoading();
    }


    private void registTo() {
        //QQ登录初始化
        if (mTencent == null) {
            mTencent = Tencent.createInstance(Global.QQ_APP_ID, this);
        }
        if (mWxApi == null) {
            //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
            mWxApi = WXAPIFactory.createWXAPI(this, "wxce488c9ce08c20e3", true);
        }

        // 将该app注册到微信
        mWxApi.registerApp("wxce488c9ce08c20e3");
        //广播监听code
        IntentFilter intentFilter = new IntentFilter(WXEntryActivity.action);
        registerReceiver(broadcastReceiver, intentFilter);
    }


    class QQCallback implements IUiListener {
        @Override
        public void onComplete(Object value) {
            baseHideWatLoading();
            L.e(value.toString());
            QQGetTokenResult qqGetTokenResult = new Gson().fromJson(value.toString(), QQGetTokenResult.class);
            System.out.println("有数据返回..");
            if (value == null) {
                return;
            }

            try {
                JSONObject jo = (JSONObject) value;

                int ret = jo.getInt("ret");

                System.out.println("json=" + String.valueOf(jo));

                if (ret == 0) {
                    showToastReal("登录成功");
                    Global.QQ_OPENID = qqGetTokenResult.getOpenid();
                    mTencent.setOpenId(qqGetTokenResult.getOpenid());
                    SpUtils.getInstace(LoginAcitivity.this).saveString("QQ_access_token", qqGetTokenResult.getAccess_token());
                    mTencent.setAccessToken(qqGetTokenResult.getAccess_token(), qqGetTokenResult.getExpires_in() + "");
                    getUser();
                    testQQ(qqGetTokenResult);
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
//

        }

        @Override
        public void onError(UiError uiError) {
            baseHideWatLoading();
        }

        @Override
        public void onCancel() {
            baseHideWatLoading();
        }
    }

    private void testQQ(QQGetTokenResult qqGetTokenResult) {

        String url = AppURL.QQ_LOGIN;

        Map map = new HashMap();
        map.put("access_token", qqGetTokenResult.getAccess_token());


        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("qqresult   ", result);

                QQCheckDateResult CheckResult = new Gson().fromJson(result, QQCheckDateResult.class);
                if (CheckResult.getCode().equals("3000")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    showToastReal(CheckResult.getMsg());
                    openActivity(AssociatePhoneActivity.class, bundle);
                } else if (CheckResult.getCode().equals(Global.RESULT_CODE)) {
                    Global.JESSIONID = CheckResult.getJsessionid();
                    Global.UserId = CheckResult.getResult().getId();
                    Global.shopId = CheckResult.getResult().getShopId();
                    Global.HeadView = CheckResult.getResult().getImgUrl();
                    Global.nickName = CheckResult.getResult().getNickName();
                    Global.isLogin = true;
                    SpUtils.getInstace(LoginAcitivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(LoginAcitivity.this).saveInt("loginType", 2);
                    Bundle bundle = new Bundle();
                    bundle.putInt("openType", 0);
                    openActivity(MainActivity.class, bundle);
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }

    ///这里获取到个人信息
    private void getUser() {
        if (mTencent != null) {
            UserInfo qqInfo = new UserInfo(this, mTencent.getQQToken());
            qqInfo.getUserInfo(new IUiListener() {
                @Override
                public void onError(UiError arg0) {
                    // TODO Auto-generated method stub
                    Log.i("---error", arg0.toString() + "");
                }

                @Override
                public void onComplete(Object arg0) {
                    // TODO Auto-generated method stub
                    if (arg0 == null) {
                        return;
                    }
                    try {
                        //获取到了数据，自己处理，具体的要自己打印出来再看怎么解析，也可以看上面的格式
                        L.e("--success", arg0.toString());
                        QQUserData qqUserData = new Gson().fromJson(arg0.toString(), QQUserData.class);
                        Global.nickName = qqUserData.getNickname();
                        Global.HeadView = qqUserData.getFigureurl_qq_2();

                    } catch (Exception e) {

                    }

                }

                @Override
                public void onCancel() {

                }
            });

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 官方文档 不然不会回调!
        mTencent.onActivityResultData(requestCode, resultCode, data, qqCallback);
        mTencent.handleResultData(data, qqCallback);

    }

    public void getWeixingToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Global.appId + "&secret=" + Global.secret + "&code=" + code + "&grant_type=authorization_code";
        VolleyRequestUtils.getInstance().getRequestGet(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                WeixinResult weixinResult = new Gson().fromJson(result, WeixinResult.class);
                Global.WX_OPENID = weixinResult.getOpenid();
                SpUtils.getInstace(LoginAcitivity.this).saveString("refresh_token", weixinResult.getRefresh_token());
                SpUtils.getInstace(LoginAcitivity.this).saveString("access_token", weixinResult.getAccess_token());
                SpUtils.getInstace(LoginAcitivity.this).saveString("openid", weixinResult.getOpenid());
                getWXDate(weixinResult);
                testWeixin();
            }

            @Override
            public void onFail(String fail) {

            }
        });
    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Bundle bundle = intent.getExtras();
            String code = bundle.getString("code");
            if (!StringUtils.isEmpty(code)) {
                getWeixingToken(code);
            }
            baseHideWatLoading();
        }
    };

    private void getWXDate(WeixinResult weixinResult) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + weixinResult.getAccess_token() + "&openid=" + weixinResult.getOpenid();
        VolleyRequestUtils.getInstance().getRequestGet(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e(result);
                WXDate wxDate = new Gson().fromJson(result, WXDate.class);
                Global.HeadView = wxDate.getHeadimgurl();
                Global.nickName = wxDate.getNickname();
            }

            @Override
            public void onFail(String fail) {
                showToastReal("数据请求失败");
            }
        });
    }

    public void testWeixin() {

        String url = AppURL.WEIXIN_LOGIN;
        String access_token = SpUtils.getInstace(this).getString("access_token");
        String openid = SpUtils.getInstace(this).getString("openid");
        Map map = new HashMap();
        map.put("access_token", access_token);
        map.put("openid", openid);


        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("weixinresult", result);

                WXLoginCheckResult wxLoginCheckResult = new Gson().fromJson(result, WXLoginCheckResult.class);
                if (wxLoginCheckResult.getCode().equals("3000")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 0);
                    showToastReal(wxLoginCheckResult.getMsg());
                    openActivity(AssociatePhoneActivity.class, bundle);
                } else if (wxLoginCheckResult.getCode().equals(Global.RESULT_CODE)) {
                    Global.JESSIONID = wxLoginCheckResult.getJsessionid();
                    Global.UserId = wxLoginCheckResult.getResult().getId();
                    Global.shopId = wxLoginCheckResult.getResult().getShopId();
                    Global.HeadView = wxLoginCheckResult.getResult().getImgUrl();
                    Global.nickName = wxLoginCheckResult.getResult().getNickName();
                    Global.isLogin = true;
                    SpUtils.getInstace(LoginAcitivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(LoginAcitivity.this).saveInt("loginType", 1);
                    Bundle bundle = new Bundle();
                    bundle.putInt("openType", 0);
                    openActivity(MainActivity.class, bundle);
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

package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
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
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
                openActivity(MainActivity.class, null);
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
        map.put("bizId",Global.BIZID);
        map.put("code",Global.CODE);
//        map.put("shopId",Global.shopId);
        map.put("loginName",phoneNumber);
        map.put("nickName",phoneNumber);
        map.put("password",etRegisterPassword.getText().toString());
//        if(!StringUtils.isEmpty(imgurl)){
//            map.put("imgUrl",imgurl);
//        }
        VolleyRequestUtils.getInstance().getRequestPost(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                loginResult = new Gson().fromJson(result,LoginResult.class);
                if(Global.RESULT_CODE.equals(loginResult.getCode())){
                    Global.UserId = loginResult.getResult().getId();
                    Global.JESSIONID=loginResult.getJsessionid();
                    Global.HeadView = AppURL.baseHost + loginResult.getResult().getImgUrl();
                    Global.shopId = loginResult.getResult().getShopId();
                    Global.isLogin =true;
                    SpUtils.getInstace(LoginAcitivity.this).saveBoolean("isExit",false);
                    openActivity(MainActivity.class,null);
                    finish();
                }else {
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
                    openActivity(MainActivity.class, null);
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
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
                showToastReal("qq登陆失败");
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                openActivity(AssociatePhoneActivity.class, null);
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                showToastReal("取消qq登陆");
            }
        });
        //authorize与showUser单独调用一个即可
        qq.authorize();//单独授权,OnComplete返回的hashmap是空的
        qq.showUser(null);//授权并获取用户信息
        //移除授权
        qq.removeAccount(true);
    }

    private void weiChatLogin() {

        Platform weibo = ShareSDK.getPlatform(Wechat.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        weibo.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
                showToastReal("微信登陆失败");
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                openActivity(AssociatePhoneActivity.class, null);

            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub
                showToastReal("取消微信登陆");
            }
        });
//authorize与showUser单独调用一个即可
        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        weibo.showUser(null);//授权并获取用户信息
//移除授权
        weibo.removeAccount(true);
    }
}

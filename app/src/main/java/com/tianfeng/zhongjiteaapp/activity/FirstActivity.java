package com.tianfeng.zhongjiteaapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tencent.tauth.Tencent;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.LoginResult;
import com.tianfeng.zhongjiteaapp.json.QQCheckDateResult;
import com.tianfeng.zhongjiteaapp.json.QQGetTokenResult;
import com.tianfeng.zhongjiteaapp.json.WXDate;
import com.tianfeng.zhongjiteaapp.json.WXLoginCheckResult;
import com.tianfeng.zhongjiteaapp.json.WeixinResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class FirstActivity extends BaseActivity {


    @Bind(R.id.flybanner)
    FlyBanner flybanner;
    @Bind(R.id.iv_into)
    ImageView ivInto;

    private String phone, password;
    private boolean isFirstCome = SpUtils.getInstace(this).getBoolean("isFirst", true);
    private Tencent mTencent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        UIUtils.setBarTint(this, false);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (isFirstCome) {
            ivInto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity(LoginAcitivity.class, null);
                    SpUtils.getInstace(FirstActivity.this).saveBoolean("isFirst", false);
                    finish();
                }
            });
            List list = new ArrayList();
            list.add(R.mipmap.one);
            list.add(R.mipmap.two);
            list.add(R.mipmap.three);
            flybanner.setImages(list);

        } else {
            boolean isExit = SpUtils.getInstace(this).getBoolean("isExit", true);
            int loginType = SpUtils.getInstace(this).getInt("loginType", 0);
            if (!isExit) {
                switch (loginType) {
                    case 0:
                        openActivity(LoginAcitivity.class, null);
                        break;
                    case 1:
                        //微信
                        getNewToken();
                        break;
                    case 2:
                        //qq
                        loginQQ();
                        break;
                    case 3:
                        //手机号
                        phone = SpUtils.getInstace(this).getString("phoneNumber");
                        password = SpUtils.getInstace(this).getString("password");
                        login(phone, password);
                        break;

                }

            } else {
                openActivity(LoginAcitivity.class, null);
            }

        }


    }

    private void loginQQ() {
        //QQ登录初始化
        if(mTencent==null){
            mTencent = Tencent.createInstance(Global.QQ_APP_ID, this);
        }
        testQQ();
    }
    private void testQQ() {

        String url = AppURL.QQ_LOGIN;
        String access_token = SpUtils.getInstace(this).getString("QQ_access_token");
        Map map = new HashMap();
        map.put("access_token", access_token);


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
                    SpUtils.getInstace(FirstActivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(FirstActivity.this).saveInt("loginType", 2);
                    openActivity(MainActivity.class,null);
                }else {
                    openActivity(LoginAcitivity.class,null);
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }


    /**
     * 刷新或续期access_token使用
     */
    private void getNewToken() {
        //https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
        String refresh_token = SpUtils.getInstace(this).getString("refresh_token");
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + Global.appId + "&grant_type=refresh_token" + "&refresh_token=" + refresh_token;
        VolleyRequestUtils.getInstance().getRequestGet(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
                try {
                    if((!jsonObject.has("errcode"))&&jsonObject.has("refresh_token")){
                        String refresh_token = jsonObject.get("refresh_token").getAsString();
                        String access_token = jsonObject.get("access_token").getAsString();
                        String openid = jsonObject.get("openid").getAsString();
                        SpUtils.getInstace(FirstActivity.this).saveString("refresh_token",refresh_token);
                        SpUtils.getInstace(FirstActivity.this).saveString("access_token",access_token);
                        SpUtils.getInstace(FirstActivity.this).saveString("openid",openid);
                        testWeixin();
                    }else{
                        showToastReal("微信授权过期，请重新登录");
                        L.e(result);
                        openActivity(LoginAcitivity.class, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String fail) {

            }
        });
    }

//    private void getWXDate() {
//        String access_token = SpUtils.getInstace(this).getString("access_token");
//        String openid = SpUtils.getInstace(this).getString("openid");
//        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
//        VolleyRequestUtils.getInstance().getRequestGet(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                L.e(result);
//                WXDate wxDate = new Gson().fromJson(result, WXDate.class);
//                Global.HeadView = wxDate.getHeadimgurl();
//                Global.nickName = wxDate.getNickname();
//            }
//
//            @Override
//            public void onFail(String fail) {
//                showToastReal("数据请求失败");
//            }
//        });
//    }

    public void testWeixin() {

        String url = AppURL.WEIXIN_LOGIN;
        String access_token = SpUtils.getInstace(this).getString("access_token");
        String openid = SpUtils.getInstace(this).getString("openid");
        Map map = new HashMap();
        map.put("access_token",access_token);
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
                    SpUtils.getInstace(FirstActivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(FirstActivity.this).saveInt("loginType", 1);
                    openActivity(MainActivity.class, null);

                }else {
                    openActivity(LoginAcitivity.class,null);
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }


    private void login(final String loginName, final String password) {

        Map map = new HashMap();
        map.put("loginName", loginName);
        map.put("password", password);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, AppURL.LOGIN_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {

                L.e("result", result);
                LoginResult loginResult = new Gson().fromJson(result, LoginResult.class);
                if (Global.RESULT_CODE.equals(loginResult.getCode())) {
                    Global.JESSIONID = loginResult.getJsessionid();
                    Global.UserId = loginResult.getResult().getId();
                    Global.shopId = loginResult.getResult().getShopId();
                    Global.HeadView = AppURL.baseHost + loginResult.getResult().getImgUrl();
                    Global.nickName = loginResult.getResult().getNickName();
                    Global.isLogin = true;
                    SpUtils.getInstace(FirstActivity.this).saveBoolean("isExit", false);
                    SpUtils.getInstace(FirstActivity.this).saveString("phoneNumber", loginName);
                    SpUtils.getInstace(FirstActivity.this).saveString("password", password);
                    openActivity(MainActivity.class, null);
                } else if (Global.FAIL_CODE.equals(loginResult.getCode())) {
                    openActivity(LoginAcitivity.class, null);
                } else {
                    showToastReal(loginResult.getMsg());
                }
//                Global.UserId =

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);

            }
        }, map);
    }


}

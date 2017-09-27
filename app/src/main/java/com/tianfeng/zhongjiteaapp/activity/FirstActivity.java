package com.tianfeng.zhongjiteaapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.LoginResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.FlyBanner;

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
private  boolean isFirstCome = SpUtils.getInstace(this).getBoolean("isFirst",true);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        UIUtils.setBarTint(this,false);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if(isFirstCome){
            ivInto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity(LoginAcitivity.class, null);
                    SpUtils.getInstace(FirstActivity.this).saveBoolean("isFirst",false);
                    finish();
                }
            });
            List list = new ArrayList();
            list.add(R.mipmap.one);
            list.add(R.mipmap.two);
            list.add(R.mipmap.three);
            flybanner.setImages(list);

        }else {
            boolean isExit = SpUtils.getInstace(this).getBoolean("isExit", true);
            if (!isExit) {
                phone = SpUtils.getInstace(this).getString("phoneNumber");
                password = SpUtils.getInstace(this).getString("password");
                login(phone,password);
            }else {
                openActivity(LoginAcitivity.class, null);
            }

        }


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

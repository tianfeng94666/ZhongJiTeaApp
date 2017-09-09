package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.BaseApplication;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

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
    @Bind(R.id.et_login_phone)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UIUtils.setBarTint(this,true);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login_cancle, R.id.tv_login_tv, R.id.tv_regisit_tv, R.id.tv_login_code, R.id.cb_ischeck, R.id.tv_agreement1, R.id.tv_agreement2, R.id.tv_next, R.id.tv_login_forget_password, R.id.tv_login, R.id.iv_weixin, R.id.iv_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_cancle:
                openActivity(MainActivity.class,null);
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
                break;
            case R.id.tv_agreement2:
                break;
            case R.id.tv_next:
                break;
            case R.id.tv_login_forget_password:
                openActivity(AssociatePhoneActivity.class,null);
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_qq:
                break;
        }
    }

    private void login() {
//        VolleyRequestUtils.getInstance().getCookieRequest(this,);
    }

    private void getLoginCode() {
        if(UIUtils.isMobileNO(etLoginPhone.getText().toString())){
            getCode();
        }else {
            showToastReal("手机号码输入错误！");
        }
    }

    private void getCode() {


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


}

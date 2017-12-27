package com.tianfeng.zhongjiteaapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.AboutResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class AboutActivity extends BaseActivity {

    @Bind(R.id.iv_app_icon)
    ImageView ivAppIcon;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;
    @Bind(R.id.tv_descript)
    TextView tvDescript;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_app_phone)
    TextView tvAppPhone;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.tv_call)
    Button tvCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        UIUtils.setBarTint(this, false);
        ButterKnife.bind(this);
        initView();
        netLoad();
    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.title_text);
        title.setText("关于我们");
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
    }

    private void netLoad() {
        Map map = new HashMap();
        String url = AppURL.APPINFO_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                AboutResult aboutResult = new Gson().fromJson(result, AboutResult.class);
                if (Global.RESULT_CODE.equals(aboutResult.getCode())) {
                    if (aboutResult.getResult() != null) {
                        tvDescript.setText("关于小茶宝：" + aboutResult.getResult().getApp_introduction());
                        tvVersion.setText("版本号：" + aboutResult.getResult().getApp_android_version());
                        tvAppPhone.setText( aboutResult.getResult().getCustomer_service_phone());
                    } else {
                        showToastReal(aboutResult.getMsg());
                    }

                } else {
                    showToastReal(aboutResult.getMsg());
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
     * 申请权限
     */
    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        ShopInformationActivity.RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                callPhone();
            }
        } else {
            callPhone();
        }
    }

    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ShopInformationActivity.RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    // Permission Denied
                    showToastReal("权限申请失败");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 拨号方法
     */
    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tvAppPhone.getText().toString()));
        startActivity(intent);
    }
}

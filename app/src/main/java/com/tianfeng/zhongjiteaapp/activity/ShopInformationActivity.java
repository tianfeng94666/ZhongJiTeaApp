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
import com.tianfeng.zhongjiteaapp.json.ShopInfoResult;
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

public class ShopInformationActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_address)
    TextView tvShopAddress;
    @Bind(R.id.tv_shop_phone)
    TextView tvShopPhone;
    @Bind(R.id.tv_shop_time)
    TextView tvShopTime;
    @Bind(R.id.tv_call)
    Button tvCall;
    private ShopInfoResult shopInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_information);
        UIUtils.setBarTint(this, false);
        ButterKnife.bind(this);
        netload();
    }

    private void initView() {
        ShopInfoResult.ResultBean resultBean = shopInfo.getResult();
        titleText.setText("门店信息");
        tvShopName.setText(resultBean.getShopName());
        tvShopAddress.setText(resultBean.getAddress());
        tvShopPhone.setText(resultBean.getTel());
        tvShopTime.setText(resultBean.getBusiStartTime() + "--" + resultBean.getBusiEndTime());
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });

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
                        RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE);
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
            case RequestPermissionType.REQUEST_CODE_ASK_CALL_PHONE:
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
        intent.setData(Uri.parse("tel:" + tvShopPhone.getText().toString()));
        startActivity(intent);
    }

    public void netload() {
        Map map = new HashMap();
        map.put("shopId", Global.shopId);
        String url = AppURL.SHOPINFO_URL + "/" + Global.shopId;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                shopInfo = new Gson().fromJson(result, ShopInfoResult.class);
                if (Global.RESULT_CODE.equals(shopInfo.getCode())) {
                    if (shopInfo.getResult() != null) {
                        initView();
                    }

                } else {
                    showToastReal(shopInfo.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }

    public interface RequestPermissionType {

        /**
         * 请求打电话的权限码
         */
        int REQUEST_CODE_ASK_CALL_PHONE = 100;
    }
}

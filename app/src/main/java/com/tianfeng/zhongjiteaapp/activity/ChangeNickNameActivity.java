package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.UserInfoResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class ChangeNickNameActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.et_nick_name)
    EditText etNickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        UIUtils.setBarTint(this,false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_right)
    public void onClick() {
        changeName();
    }

    private void changeName() {
        if(StringUtils.isEmpty(etNickName.getText().toString())){
            showToastReal("不能为空");
            return;
        }
        Map map = new HashMap();
        map.put("userId", Global.UserId);
        map.put("nickName",etNickName.getText().toString());
        String url = AppURL.CHANGE_NAME_URL;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                UserInfoResult userinfoResult = new Gson().fromJson(result, UserInfoResult.class);
                if (Global.RESULT_CODE.equals(userinfoResult.getCode())) {
                    if (userinfoResult.getResult() != null) {
                        Global.nickName = etNickName.getText().toString();
                        showToastReal("修改成功");
                        finish();
                    }

                } else {
                    showToastReal(userinfoResult.getMsg());
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

package com.tianfeng.zhongjiteaapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DialogActivity extends BaseActivity {
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    Activity activity;
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        tvMessage.setText(bundle.getString("key"));
        activity = (Activity) bundle.getSerializable("activity");
    }

    @OnClick(R.id.tv_confirm)
    public void onClick() {
        finish();
        if(PledgeActivity.instance!=null){
            PledgeActivity.instance.finish();
            openActivity(MainActivity.class,null);
        }
        if(StorageActivity.instance!=null){
            StorageActivity.instance.finish();
            openActivity(MainActivity.class,null);
        }
        if(XianTiActivity.instance!=null){
            XianTiActivity.instance.finish();
            openActivity(MainActivity.class,null);
        }
        if(ChangeActivity.instance!=null){
            ChangeActivity.instance.finish();
            openActivity(MainActivity.class,null);
        }
        if(DepositActivity.instance!=null){
            DepositActivity.instance.finish();
            openActivity(MainActivity.class,null);
        }
    }
}

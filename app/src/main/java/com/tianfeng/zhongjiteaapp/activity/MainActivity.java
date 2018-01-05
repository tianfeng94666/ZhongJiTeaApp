package com.tianfeng.zhongjiteaapp.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.fragment.HomeFragment;
import com.tianfeng.zhongjiteaapp.fragment.MallFragment;
import com.tianfeng.zhongjiteaapp.fragment.MineFragment;
import com.tianfeng.zhongjiteaapp.fragment.StorageFragment;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.menu_line)
    View menuLine;
    @Bind(R.id.id_ig_home)
    ImageView igHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.tab1_count)
    TextView tab1Count;
    @Bind(R.id.id_fl_tab1)
    FrameLayout id_fl_tab1;
    @Bind(R.id.iv_mall)
    ImageView igMall;
    @Bind(R.id.tv_mall)
    TextView tvMall;
    @Bind(R.id.tab2_count)
    TextView tab2Count;
    @Bind(R.id.id_fl_tab2)
    FrameLayout id_fl_tab2;
    @Bind(R.id.iv_storage)
    ImageView igStorage;
    @Bind(R.id.tv_help)
    TextView tvStorage;
    @Bind(R.id.id_fl_tab3)
    LinearLayout id_fl_tab3;
    @Bind(R.id.iv_mine)
    ImageView ivMine;
    @Bind(R.id.tv_mine)
    TextView tvMine;
    @Bind(R.id.id_fl_tab4)
    LinearLayout id_fl_tab4;
    int selectPosition;
    static  BaseActivity intance;
    public Fragment homeFragment, mallFragment, storageFragment, mineFrament;
    private FragmentManager fragmentMag;
    public int openType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDate(getIntent());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intance=this;
        initView();


    }

    private void getDate(Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            openType = bundle.getInt("openType");
        }
    }

    public int getOpenType() {
        return openType;
    }

    public void setOpenType(int openType) {
        this.openType = openType;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setChioceFragment(Global.selectPosition);
        if (!Global.isLogin) {
            setChioceFragment(0);
        }
    }

    private void initView() {

        fragmentMag = getSupportFragmentManager();

        id_fl_tab3.setOnClickListener(this);
        id_fl_tab2.setOnClickListener(this);
        id_fl_tab1.setOnClickListener(this);
        id_fl_tab4.setOnClickListener(this);
        selectPosition = Global.selectPosition;
        setChioceFragment(selectPosition);
        TextView hindInformation = (TextView) findViewById(R.id.tab2_count);
    }


    public void setChioceFragment(int index) {
        FragmentTransaction fragTrans = fragmentMag.beginTransaction();
        resetAllFragmentView();
        hideAllFragments(fragTrans);
        switch (index) {
            case 0:
                tvHome.setTextColor(getResources().getColor(R.color.theme_color));
                igHome.setImageResource(R.mipmap.home_choose);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragTrans.add(R.id.content, homeFragment);
                } else {
                    fragTrans.show(homeFragment);
                }
                Global.selectPosition = 0;
                break;
            case 1:
                tvMall.setTextColor(getResources().getColor(R.color.theme_color));
                igMall.setImageResource(R.mipmap.mall_choose);
                if (mallFragment == null) {
                    mallFragment = new MallFragment();
                    fragTrans.add(R.id.content, mallFragment);
                } else {
                    fragTrans.show(mallFragment);
                }

                Global.selectPosition = 1;
                break;
            case 2:
                tvStorage.setTextColor(getResources().getColor(R.color.theme_color));
                igStorage.setImageResource(R.mipmap.storage_choose);
                if (storageFragment == null) {
                    storageFragment = new StorageFragment();
                    fragTrans.add(R.id.content, storageFragment);
                } else {
                    fragTrans.show(storageFragment);
                }

                Global.selectPosition = 2;
                break;
            case 3:
                tvMine.setTextColor(getResources().getColor(R.color.theme_color));
                ivMine.setImageResource(R.mipmap.mine_choose);
                if (mineFrament == null) {
                    mineFrament = new MineFragment();
                    fragTrans.add(R.id.content, mineFrament);
                } else {
                    fragTrans.show(mineFrament);
                }
                Global.selectPosition = 3;
                break;

        }

        fragTrans.commit();

    }

    private void resetAllFragmentView() {
        igHome.setImageResource(R.mipmap.home_unchoose);
        igMall.setImageResource(R.mipmap.mall_unchoose);
        igStorage.setImageResource(R.mipmap.storage_unchoose);
        ivMine.setImageResource(R.mipmap.mine_unchoose);

        tvHome.setTextColor(getResources().getColor(R.color.text_color3));
        tvMall.setTextColor(getResources().getColor(R.color.text_color3));
        tvStorage.setTextColor(getResources().getColor(R.color.text_color3));
        tvMine.setTextColor(getResources().getColor(R.color.text_color3));

    }

    private void hideAllFragments(FragmentTransaction fragTrans) {
        if (homeFragment != null) {
            fragTrans.hide(homeFragment);
        }
        if (storageFragment != null) {
            fragTrans.hide(storageFragment);
        }
        if (mallFragment != null) {
            fragTrans.hide(mallFragment);
        }

        if (mineFrament != null) {
            fragTrans.hide(mineFrament);
        }
    }

    @Override
    public void onClick(View v) {
        CommMethod.isLogin(this);
        switch (v.getId()) {
            case R.id.id_fl_tab1:
                setChioceFragment(0);
                break;
            case R.id.id_fl_tab2:
                setChioceFragment(1);
                break;
            case R.id.id_fl_tab3:
                setChioceFragment(2);
                break;
            case R.id.id_fl_tab4:
                setChioceFragment(3);
                break;

        }
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

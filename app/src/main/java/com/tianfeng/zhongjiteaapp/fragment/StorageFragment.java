package com.tianfeng.zhongjiteaapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.activity.ChooseShopDialogActivity;
import com.tianfeng.zhongjiteaapp.activity.StorageActivity;
import com.tianfeng.zhongjiteaapp.base.BaseFragment;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class StorageFragment extends BaseFragment {

    @Bind(R.id.tl_title)
    TabLayout tab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private PagerAdapter pagerAdapter;
    public List<Fragment> fragmentList = new ArrayList<>();
    private StorageDetailFragment yunNanStorageFragemnt;
    private StorageDetailFragment huaNanStorageFragment;
    static int currentItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_storage, null);
        ButterKnife.bind(this, view);
        UIUtils.setBarTint(getActivity(), false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        titleText.setText("仓储管理");
        idIgBack.setVisibility(View.GONE);
        tvRight.setImageResource(R.mipmap.add);
        tvRight.setVisibility(View.VISIBLE);
        tab.addTab(tab.newTab().setText("1"));
        tab.addTab(tab.newTab().setText("2"));
        //添加页卡标题
        mTitleList.add("华南仓");
        mTitleList.add("云南仓");
        yunNanStorageFragemnt = new StorageDetailFragment(1);
        fragmentList.add(yunNanStorageFragemnt);
        huaNanStorageFragment = new StorageDetailFragment(2);
        fragmentList.add(huaNanStorageFragment);
        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(currentItem);
        tab.setupWithViewPager(viewpager);
        tab.setTabsFromPagerAdapter(pagerAdapter);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStoreTea();
            }
        });
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addStoreTea() {
        if (StringUtils.isEmpty(Global.shopId)) {
            Bundle bundle = new Bundle();
            bundle.putInt("nextStep",1);
            openActivity(ChooseShopDialogActivity.class, bundle);
        } else {
            openActivity(StorageActivity.class, null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private List<?> fragments;

        public PagerAdapter(FragmentManager fm, List<?> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {

            return (Fragment) fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }
    }


}

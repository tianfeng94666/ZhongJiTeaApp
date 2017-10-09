package com.tianfeng.zhongjiteaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.HelpResult;
import com.tianfeng.zhongjiteaapp.json.NoticeResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.xListView.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.tianfeng.zhongjiteaapp.utils.ToastManager.showToastReal;

/**
 * Created by 田丰 on 2017/9/26.
 */

public class HistoryNoticeActivity extends BaseActivity implements XListView.IXListViewListener {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.lv_notices)
    XListView lvNotices;
    private NoticeResult noticeResult;
    private int index = 1;
    private int maxIndex;
    private List<NoticeResult.ResultBeanX.ResultBean> noticeList = new ArrayList();
    private CommonAdapter<NoticeResult.ResultBeanX.ResultBean> noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_notices);
        ButterKnife.bind(this);
        UIUtils.setBarTint(this, false);
        initView();
        getNotice();
    }

    private void initView() {
        titleText.setText("历史公告");
        lvNotices.setXListViewListener(this);
        lvNotices.setAutoLoadEnable(false);
        lvNotices.setPullRefreshEnable(false);
        lvNotices.setPullLoadEnable(true);
    }

    private void getNotice() {
        Map map = new HashMap();
        map.put("index", index);
        map.put("pageSize", 10);
        VolleyRequestUtils.getInstance().getRequestPost(this, AppURL.GET_NOTICE_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result ", result);
                L.e("url  ", AppURL.GET_NOTICE_URL);
                noticeResult = new Gson().fromJson(result, NoticeResult.class);
                if (Global.RESULT_CODE.equals(noticeResult.getCode())) {
                    List temp = noticeResult.getResult().getResult();
                    maxIndex = noticeResult.getResult().getTotalPage();
                    if (maxIndex >= index) {
                        noticeList.addAll(temp);
                    } else {
                        showToastReal("已经是全部数据了");
                    }
                    setLv();

                } else {
                    showToastReal(noticeResult.getMsg());
                }
                lvNotices.stopRefresh();
                lvNotices.stopLoadMore();

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                lvNotices.stopRefresh();
                lvNotices.stopLoadMore();

            }
        }, map);
    }

    private void setLv() {
        if (noticeAdapter == null) {
            noticeAdapter = new CommonAdapter<NoticeResult.ResultBeanX.ResultBean>(noticeList, R.layout.item_notice) {
                @Override
                public void convert(int position, BaseViewHolder helper, NoticeResult.ResultBeanX.ResultBean item) {
                    helper.setText(R.id.tv_help_name, noticeList.get(position).getTitle());
                    helper.setText(R.id.tv_time, CommMethod.getFormatedDateTime(noticeList.get(position).getCreateTime()));
                }


            };
            lvNotices.setAdapter(noticeAdapter);
            lvNotices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "1");
                    if (noticeList != null && noticeList.size() > 0) {
                        bundle.putString("title", noticeList.get(position - 1).getTitle());
                        bundle.putString("content", noticeList.get(position - 1).getContent());
                        openActivity(TextActivity.class, bundle);
                    }
                }
            });
        } else {
            noticeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {
        index = 1;
        noticeList.clear();
        noticeAdapter.notifyDataSetChanged();
        getNotice();
    }

    @Override
    public void onLoadMore() {
        index++;
        getNotice();
    }
}

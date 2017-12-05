package com.tianfeng.zhongjiteaapp.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.json.OrderBean;
import com.tianfeng.zhongjiteaapp.json.SimpleRequestResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class DepositActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_lable)
    TextView tvLable;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    private int year;
    private int month;
    private int day;
    private OrderBean item;
    static BaseActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        ButterKnife.bind(this);
        instance= this;
        getDate();
        initView();
    }

    private void getDate() {
        Bundle bundle = getIntent().getExtras();
        item = (OrderBean) bundle.getSerializable("storageItem");
    }
    private void initView() {
        titleText.setText("选择续存日期");
    }

    @OnClick({R.id.tv_date, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                initData();
                break;
            case R.id.tv_confirm:
                comfirm();
                break;
        }
    }

    private void comfirm() {
        if (StringUtils.isEmpty(tvDate.getText().toString())){
            showToastReal("请输入数量");
            return;
        }
        Map map = new HashMap();
        map.put("id", item.getId());
        map.put("date",tvDate.getText().toString());
        String url = AppURL.DEPOSIT_REQUEST;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                SimpleRequestResult getData = new Gson().fromJson(result, SimpleRequestResult.class);
                if (Global.RESULT_CODE.equals(getData.getCode())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", getData.getResult());
                    openActivity(DialogActivity.class, bundle);

                } else {
                    showToastReal(getData.getMsg());
                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);
    }
    public void initData() {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        String dateString =tvDate.getText().toString();
        if(StringUtils.isEmpty(dateString)){
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            date = dateFromString( getDate(year, month, day));
        }else {
            date = dateFromString(dateString);
        }
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                String s = getDate(mYear, mMonth, mDay);
                tvDate.setText(s);
            }
        }, year, month, day);
        datePickerDialog.show();


    }
    /**
     * 将yyyy-MM-dd string转化为date
     *
     * @param st
     * @return
     */
    public Date dateFromString(String st) {
        Date date = null;
        // 设置传入的时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(st);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得格式2015-09-01类似的String
     *
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public String getDate(int mYear, int mMonth, int mDay) {
        StringBuilder sb = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay);
        String st = sb.toString();
        return st;
    }
}

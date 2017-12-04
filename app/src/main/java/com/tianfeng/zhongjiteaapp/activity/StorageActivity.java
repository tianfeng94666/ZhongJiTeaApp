package com.tianfeng.zhongjiteaapp.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.dialog.ProtocolDialog;
import com.tianfeng.zhongjiteaapp.json.PledgeResult;
import com.tianfeng.zhongjiteaapp.json.Product;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.popupwindow.ShopsChoosePopupWindow;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class StorageActivity extends BaseActivity {


    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.tv_store_name)
    TextView tvStoreName;
    @Bind(R.id.tv_tea_name)
    TextView tvTeaName;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.tv_tea_type)
    TextView tvTeaType;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.et_tea_amount)
    EditText etTeaAmount;
    @Bind(R.id.et_tea_price)
    EditText etTeaPrice;
    @Bind(R.id.et_tea_totol)
    EditText etTeaTotol;
    @Bind(R.id.tv_store_date)
    TextView tvStoreDate;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.rootview)
    LinearLayout rootview;
    private Product product;
    private ShopsChoosePopupWindow chooseStoragePopupwindow;
    private int year;
    private int month;
    private int day;
    private ProtocolDialog dialog;
    private String deportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        ButterKnife.bind(this);
        chooseStoragePopupwindow = new ShopsChoosePopupWindow(this, 1);
        initView();
    }

    private void initView() {
        titleText.setText("输入暂存信息");
    }

    @OnClick({R.id.tv_store_name, R.id.tv_tea_name, R.id.tv_store_date, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_store_name:
                SearchStore();
                break;
            case R.id.tv_tea_name:
                SearchTea();
                break;
            case R.id.tv_store_date:
                initData();
                break;
            case R.id.tv_next:
                goToNext();
                break;
        }
    }

    private void goToNext() {
        if (!judgeData()) {
            return;
        }
        Map map = new HashMap();

        map.put("userId", Global.UserId);
        map.put("goodsId", product.getId());
        map.put("deportId", deportId);
        map.put("price", etTeaPrice.getText().toString());
        map.put("quantity", etTeaAmount.getText().toString());
        map.put("total", etTeaTotol.getText().toString());
        map.put("endTime", tvStoreDate.getText().toString());

        String url = AppURL.STORAGE_REQUEST;
        L.e("url", url);
        VolleyRequestUtils.getInstance().getStringPostRequest(this, url, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
//                PledgeResult getData = new Gson().fromJson(result, PledgeResult.class);
//                if (Global.RESULT_CODE.equals(getData.getCode())) {
//                    showComfirmDialog();
//                } else {
//                    showToastReal(getData.getMsg());
//                }

            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                showToastReal(fail);
            }
        }, map);


    }

    private boolean judgeData() {
        if (StringUtils.isEmpty(tvStoreName.getText().toString())) {
            showToastReal("请选择仓储");
            return false;
        }
        if (StringUtils.isEmpty(tvTeaName.getText().toString()) || product == null) {
            showToastReal("请选择茶品");
            return false;
        }
        if (StringUtils.isEmpty(etTeaAmount.getText().toString())) {
            showToastReal("请输入成交数量");
            return false;
        }
        if (StringUtils.isEmpty(etTeaPrice.getText().toString())) {
            showToastReal("请输入成交单价");
            return false;
        }
        if (StringUtils.isEmpty(tvStoreName.getText().toString())) {
            showToastReal("请输入成交总金额");
            return false;
        }
        if (StringUtils.isEmpty(tvStoreDate.getText().toString())) {
            showToastReal("选择暂存时间");
            return false;
        }
        return true;
    }

    private void showComfirmDialog() {
        View view = View.inflate(this, R.layout.dialog_protocol, null);
        dialog = new ProtocolDialog(this, view, R.style.protocol_dialog_theme);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvTitle.setText("XX协议");
        tvContent.setText("有没有");
        tvConfirm.setText("同意并继续");
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastReal("okok");
            }
        });
//        dialog.setCancelable(false);
        dialog.show();
    }

    private void SearchStore() {
        final List<String> list = new ArrayList<>();
        list.add("华南仓");
        list.add("云南仓");
        chooseStoragePopupwindow.setStrings(list, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    deportId = "0001";
                } else if (position == 1) {
                    deportId = "0002";
                }
                tvStoreName.setText(list.get(position));
                chooseStoragePopupwindow.closePopupWindow();
            }
        });
        chooseStoragePopupwindow.setTitle("请选择仓库");
        chooseStoragePopupwindow.showPop(rootview);
    }

    private void SearchTea() {
        Intent intent = new Intent(StorageActivity.this, SearchTeaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "chooseTea");
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    product = (Product) bundle.getSerializable("product");
                    tvTeaName.setText(product.getGoodsName());
                    tvTeaType.setText(product.getUnit());
                }
                break;

            default:
                break;
        }
    }

    public void initData() {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        String dateString = tvStoreDate.getText().toString();
        if (StringUtils.isEmpty(dateString)) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            date = dateFromString(getDate(year, month, day));
        } else {
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
                tvStoreDate.setText(s);
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

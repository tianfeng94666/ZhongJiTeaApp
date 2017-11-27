package com.tianfeng.zhongjiteaapp.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.adapter.BaseViewHolder;
import com.tianfeng.zhongjiteaapp.adapter.CommonAdapter;
import com.tianfeng.zhongjiteaapp.json.GetShopsResult;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.type;

/**
 * Created by 田丰 on 2017/9/17.
 */

public class ShopsChoosePopupWindow {
    private  int type=0;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.lv_shop)
    ListView lvShop;
    private Context context;
    private PopupWindow popupWindow;

    public ShopsChoosePopupWindow(Context context) {
        this.context = context;
        initView();
    }
    public ShopsChoosePopupWindow(Context context ,int type) {
        this.context = context;
        initView();
        this.type =type;
    }
    public void setTitle(String st) {
            tvTitle.setText(st);
    }

    private void initView() {
        View view = View.inflate(context, R.layout.popupwindow_shops, null);
        ButterKnife.bind(this, view);
        if(type==1){
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getWindowHight() / 3);
        }else {
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getWindowHight() / 2);
        }

//        llIsshow = (LinearLayout) view.findViewById(R.id.ll_isshow);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !popupWindow.isFocusable()) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    public void setLvShop(final List<GetShopsResult.Shop> shops, AdapterView.OnItemClickListener onItemClickListener) {
        lvShop.setAdapter(new CommonAdapter<GetShopsResult.Shop>(shops, R.layout.item_textview) {
            @Override
            public void convert(int position, BaseViewHolder helper, GetShopsResult.Shop item) {
                helper.setText(R.id.item_tv, item.getShopName());
            }
        });
        lvShop.setOnItemClickListener(onItemClickListener);
    }
 public void setStrings(final List<String> strings,AdapterView.OnItemClickListener onItemClickListener){
     lvShop.setAdapter(new CommonAdapter<String>(strings,R.layout.item_textview) {
         @Override
         public void convert(int position, BaseViewHolder helper, String item) {
             helper.setText(R.id.item_tv, item);
         }
     });
     lvShop.setOnItemClickListener(onItemClickListener);
 }
    public void showPop(View view) {
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);


    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public void closePopupWindow() {
        popupWindow.dismiss();
        setBackgroundAlpha(1f);//设置屏幕透明度
    }
}

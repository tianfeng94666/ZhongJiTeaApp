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
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.inter.ChildChangeInterface;
import com.tianfeng.zhongjiteaapp.json.GetAearResult;
import com.tianfeng.zhongjiteaapp.json.GetShopsResult;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class AearChoosePopupWindow {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.lv_aears)
    ListView lvAears;
    @Bind(R.id.lv_aers_child)
    ListView lvAersChild;
    private Context context;
    private PopupWindow popupWindow;
    private List<GetAearResult.ResultBean.ChildrenBean> childrenBeanList;
    private CommonAdapter<GetAearResult.ResultBean.ChildrenBean> childAdapter;
    public ChildChangeInterface child;
    private int selectIndex;


    public AearChoosePopupWindow(Context context, List<GetAearResult.ResultBean.ChildrenBean> childrenBeanList) {
        this.context = context;
        this.childrenBeanList = childrenBeanList;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.popupwindow_aears, null);
        ButterKnife.bind(this, view);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getWindowHight() / 2);
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
    }

    public void setLvAears(final List<GetAearResult.ResultBean> aears, AdapterView.OnItemClickListener onItemClickListenerchild) {
        final CommonAdapter aerasAdapter = new CommonAdapter<GetAearResult.ResultBean>(aears, R.layout.item_textview) {
            @Override
            public void convert(int position, BaseViewHolder helper, GetAearResult.ResultBean item) {
                helper.setText(R.id.item_tv, item.getName());
                if (selectIndex == position) {
                    helper.getView(R.id.ll_item).setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                } else {
                    helper.getView(R.id.ll_item).setBackgroundColor(context.getResources().getColor(R.color.white));
                }

            }

        };
        lvAears.setAdapter(aerasAdapter);
        lvAears.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Global.AeraFirst = aears.get(position).getName();
                childrenBeanList = aears.get(position).getChildren();
                ((ChildChangeInterface) context).change(childrenBeanList);
                selectIndex = position;
                setChildAdapter();
                aerasAdapter.notifyDataSetChanged();
            }
        });

        setChildAdapter();
        lvAersChild.setOnItemClickListener(onItemClickListenerchild);
    }

    private void setChildAdapter() {
        childAdapter = new CommonAdapter<GetAearResult.ResultBean.ChildrenBean>(childrenBeanList, R.layout.item_textview) {
            @Override
            public void convert(int position, BaseViewHolder helper, GetAearResult.ResultBean.ChildrenBean item) {
                helper.setText(R.id.item_tv, item.getName());
            }

        };
        lvAersChild.setAdapter(childAdapter);
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

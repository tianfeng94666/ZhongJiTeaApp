package com.tianfeng.zhongjiteaapp.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.CommMethod;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by 田丰 on 2017/9/11.
 */

public class SharedPopupWindow {
    Context context;
    @Bind(R.id.tv_share_weichat)
    TextView tvShareWeichat;
    @Bind(R.id.tv_share_friend)
    TextView tvShareFriend;
    @Bind(R.id.tv_share_qq)
    TextView tvShareQq;
    @Bind(R.id.tv_share_weibo)
    TextView tvShareWeibo;
    @Bind(R.id.tv_share_cancle)
    TextView tvShareCancle;
    private PopupWindow popupWindow;

    public SharedPopupWindow(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.popupwindow_shared, null);
        ButterKnife.bind(this, view);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(191));
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
    @OnClick({R.id.tv_share_weichat, R.id.tv_share_friend, R.id.tv_share_qq, R.id.tv_share_weibo, R.id.tv_share_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share_weichat:
                weichatShare();
                break;
            case R.id.tv_share_friend:
                friendShare();
                break;
            case R.id.tv_share_qq:
                qqShare();
                break;
            case R.id.tv_share_weibo:
                weiboShare();
                break;
            case R.id.tv_share_cancle:
                closePopupWindow();
                break;
        }
    }

    private void weiboShare() {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText("测试分享的文本");
        sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.SSOSetting(true);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ToastManager.showToastReal("分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastManager.showToastReal("分享失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                ToastManager.showToastReal("分享取消");
            }
        }); // 设置分享事件回调
// 执行图文分享
        weibo.share(sp);
    }

    private void qqShare() {
        QQ.ShareParams sp = new QQ.ShareParams();
        sp.setText("测试分享的文本");
        sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                popupWindow.dismiss();
                ToastManager.showToastReal("分享成功");
            }
            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastManager.showToastReal("分享失败");
            }
            @Override
            public void onCancel(Platform platform, int i) {
                ToastManager.showToastReal("分享取消");
            }
        });
        qq.share(sp);
    }

    private void friendShare() {
       Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText("测试分享的文本");
        sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                popupWindow.dismiss();
                ToastManager.showToastReal("分享成功");
            }
            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastManager.showToastReal("分享失败");
            }
            @Override
            public void onCancel(Platform platform, int i) {
                ToastManager.showToastReal("分享取消");
            }
        });
        wechatMoments.share(sp);
    }

    private void weichatShare() {
        Wechat.ShareParams sp = new Wechat.ShareParams();
        sp.setText("测试分享的文本");
        sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                popupWindow.dismiss();
                ToastManager.showToastReal("分享成功");
            }
            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastManager.showToastReal("分享失败");
            }
            @Override
            public void onCancel(Platform platform, int i) {
                ToastManager.showToastReal("分享取消");
            }
        });
        wechat.share(sp);
    }
}

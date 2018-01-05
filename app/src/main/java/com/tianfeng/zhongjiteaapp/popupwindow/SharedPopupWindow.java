package com.tianfeng.zhongjiteaapp.popupwindow;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.bean.ShareContent;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
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
    private ShareContent shareContent;
    public SharedPopupWindow(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.popupwindow_shared, null);
        ButterKnife.bind(this, view);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(191));
//        llIsshow = (LinearLayout) view.findViewById(R.id.ll_isshow);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
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
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());     //点击外部消失这句很重要
    }
    public void showPop(View view,ShareContent shareContent) {
        this.shareContent = shareContent;
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
        SinaWeibo.ShareParams oks = new SinaWeibo.ShareParams();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(shareContent.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareContent.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent.getText());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(shareContent.getImagUrl());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareContent.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Androidapp下载地址");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareContent.getUrl());
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        oks.setShareType(Platform.SHARE_WEBPAGE);
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
        weibo.share(oks);
        closePopupWindow();
    }

    private void qqShare() {
        QQ.ShareParams oks = new QQ.ShareParams();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(shareContent.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareContent.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent.getText());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(shareContent.getImagUrl());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareContent.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Androidapp下载地址");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareContent.getUrl());
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {
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
        });
        qq.share(oks);
        closePopupWindow();
    }

    private void friendShare() {
       Platform.ShareParams oks = new Platform.ShareParams();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(shareContent.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareContent.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent.getText());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        if(StringUtils.isEmpty(shareContent.getImagUrl())){
            Bitmap bitmap =((BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
            oks.setImageData(bitmap);
        }else {
            oks.setImageUrl(shareContent.getImagUrl());
        }
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareContent.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("Androidapp下载地址");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareContent.getUrl());
        oks.setShareType(Platform.SHARE_WEBPAGE);
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(new PlatformActionListener() {
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
        });
        wechatMoments.share(oks);
        closePopupWindow();
    }

    private void weichatShare() {
        Platform.ShareParams oks = new Platform.ShareParams();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(shareContent.getTitle());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shareContent.getText());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(shareContent.getImagUrl());
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareContent.getUrl());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareContent.getUrl());
        oks.setShareType(Platform.SHARE_WEBPAGE);
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                ToastManager.showToastReal("分享成功");
            }
            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastManager.showToastReal("分享失败");
                L.e(throwable.toString());
            }
            @Override
            public void onCancel(Platform platform, int i) {
                ToastManager.showToastReal("分享取消");
            }
        });
        wechat.share(oks);
        closePopupWindow();
    }
}

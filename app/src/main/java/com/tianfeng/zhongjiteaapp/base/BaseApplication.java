package com.tianfeng.zhongjiteaapp.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.RequestQueue;
import com.tianfeng.zhongjiteaapp.net.ImageLoderUtils;
import com.tianfeng.zhongjiteaapp.net.VolleySingleton;
import com.tianfeng.zhongjiteaapp.utils.ACache;
import com.tianfeng.zhongjiteaapp.utils.Constants;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.Part;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Headers;
import okhttp3.Interceptor;

public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication = null;
    private static Looper mMainThreadLooper = null;
    private static Handler mMainThreadHandler = null;
    private static int mMainThreadId;
    private static Thread mMainThread = null;
    private static String token;
    public static int mNetWorkState;
    // volley请求队列
    public static RequestQueue requestQueue = null;
    private static String userPic;

    public static ACache mAcache = null;

    public static String getUserPic() {
        return userPic;
    }

    public static  void setUserPic(String userPic) {
        BaseApplication.userPic = userPic;
    }

    public  static SpUtils spUtils;


    // 共享变量
    public static final int FLUSH_MAIN_ACTIVITY=1;
    private Handler main;
    public void setMainHandler(Handler handler){
        main=handler;
    }
    public void flushMain(){
        main.sendEmptyMessage(FLUSH_MAIN_ACTIVITY);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        BaseApplication.mBaseApplication = this;
        BaseApplication.mMainThreadLooper = getMainLooper();
        BaseApplication.mMainThreadHandler = new Handler();
        BaseApplication.mMainThreadId = android.os.Process.myTid();
        BaseApplication.mMainThread = Thread.currentThread();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        spUtils = SpUtils.getInstace(this);
        mAcache=ACache.get(this);
        initVolley();
        initImageLoder();
        initOkHttpFinal();
        initParams();
        //初始化分享
        ShareSDK.initSDK(this);
    }

    private void initParams() {

    }

    public static final int REQ_TIMEOUT = 35000;
    private void initOkHttpFinal() {
        List<Part> commomParams = new ArrayList<>();
        Headers commonHeaders = new Headers.Builder().build();
        List<Interceptor> interceptorList = new ArrayList<>();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder()
                .setCommenParams(commomParams)
                .setCommenHeaders(commonHeaders)
                .setTimeout(REQ_TIMEOUT)
                .setInterceptors(interceptorList)
                .setDebug(true);
        OkHttpFinal.getInstance().init(builder.build());
    }



    public void initImageLoder() {
        ImageLoderUtils.initImageLoader(this);
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        BaseApplication.token = token;
    }

    private Context getContext(){
        return  this.getApplicationContext();
    }


    public static BaseApplication getApplication() {
        return mBaseApplication;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    //初始化 volley请求队列
    public void initVolley() {
        requestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
    }



}

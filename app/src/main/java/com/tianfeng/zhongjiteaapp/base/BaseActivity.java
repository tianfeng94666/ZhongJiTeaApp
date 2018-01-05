package com.tianfeng.zhongjiteaapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.utils.SpUtils;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.LoadingWaitDialog;

import java.util.Timer;
import java.util.TimerTask;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpTaskHandler;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.tianfeng.zhongjiteaapp.utils.UIUtils.FlymeSetStatusBarLightMode;
import static com.tianfeng.zhongjiteaapp.utils.UIUtils.MIUISetStatusBarLightMode;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class BaseActivity extends FragmentActivity implements HttpCycleContext {

    public Class<?> nextActivity;
    public static final String GET_TO = "GET_TO";
    public static final String JUMP_TO_ACTIVITY = "987";
    protected Context context;
    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        UIUtils.setBarTint(this,false);

    }

    public void showToastReal(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
    public void onBack(View view){
        finish();
    }

    private LoadingWaitDialog loadingDialog;

    protected void baseShowWatLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingWaitDialog(this, getString(R.string.pull_to_refresh_footer_refreshing_label));
            loadingDialog.show();
        }
        //SystemClock.sleep(1000);
    }

    public void baseHideWatLoading() {
        if (loadingDialog == null) return;
        if (loadingDialog != null || loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }


    public View getRootView() {
        return this.getRootView();
    }

    /**
     * 设置添加屏幕的背景透明度
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

//	// 禁止系统返回
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

    /**
     * 禁止换行
     */
    public static void notAllowedWrap(EditText view) {
        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                try {
                    return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        });
    }

    // 点击屏幕 关闭输入弹出框
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (loadingDialog != null ) {
                loadingDialog.cancel();
                loadingDialog = null;
            }
        }


        return super.onKeyDown(keyCode, event);
    }

    /**
     * 延迟去往新的Activity
     *
     * @param
     * @param cls
     * @param delay
     */
    public void delayToActivity(final Class<?> cls, long delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openActivity(cls, null);
            }
        }, delay);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public DisplayMetrics getScreen() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author wenjundu
     */
    public class PopupDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    //获取状态栏高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //BaseApplication.requestQueue.cancelAll(HTTP_TASK_KEY);
        HttpTaskHandler.getInstance().removeTask(HTTP_TASK_KEY);
//		ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//		ViewServer.get(this).setFocusedWindow(this);
    }
//	private long exitTime = 0;
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//			if (System.currentTimeMillis() - exitTime > 2000) {
//				exitTime = System.currentTimeMillis();
//			} else {
//				//moveTaskToBack(true);
//				AppManager.getAppManager().AppExit(this);
//			}
//		}
//		return true;
//	}
}

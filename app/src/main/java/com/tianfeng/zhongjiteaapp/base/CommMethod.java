package com.tianfeng.zhongjiteaapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.tianfeng.zhongjiteaapp.activity.LoginAcitivity;
import com.tianfeng.zhongjiteaapp.json.CollectedResult;
import com.tianfeng.zhongjiteaapp.net.VolleyRequestUtils;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.StringUtils;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 田丰 on 2017/9/19.
 */

public class CommMethod {
    /**
     * 收藏
     *
     * @param id
     */
    public static void collected(Activity context, String id) {
        CommMethod.isLogin(context);
        Map map = new HashMap();
        map.put("userId", Global.UserId);
        map.put("goodsId", id);
        L.e("map", map.toString());
        L.e("url=", AppURL.COLLECTED_URL);
        VolleyRequestUtils.getInstance().getRequestPost(context, AppURL.COLLECTED_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                CollectedResult collectedResult = new Gson().fromJson(result, CollectedResult.class);
                if (Global.RESULT_CODE.equals(collectedResult.getCode())) {
                    ToastManager.showToastReal("收藏成功");
                } else {
                    ToastManager.showToastReal(collectedResult.getMsg());
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                ToastManager.showToastReal(fail);
            }
        }, map);
    }

    /**
     * 取消收藏
     */
    public static void uncollected(Activity context, String id) {
        CommMethod.isLogin(context);
        Map map = new HashMap();
        map.put("userId", Global.UserId);
        map.put("goodsId", id);
        L.e("map", map.toString());
        L.e("url=", AppURL.UNCOLLECTED_URL);
        VolleyRequestUtils.getInstance().getRequestPost(context, AppURL.UNCOLLECTED_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                CollectedResult collectedResult = new Gson().fromJson(result, CollectedResult.class);
                if (Global.RESULT_CODE.equals(collectedResult.getCode())) {
                    ToastManager.showToastReal("取消收藏成功");
                } else {
                    ToastManager.showToastReal(collectedResult.getMsg());
                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
//                ToastManager.showToastReal(fail);
            }
        }, map);
    }

    /**
     * 判断是否登入
     */
    public static void isLogin(Activity context) {
        if (!Global.isLogin) {
            ToastManager.showToastReal("请先注册登录！");
            Intent intent = new Intent(context, LoginAcitivity.class);
            context.startActivity(intent);
        }
    }

    public static String getState(String st) {
        if ("0".equals(st)) {
            return "待审核";
        } else if ("1".equals(st)) {
            return "审核通过";
        } else if ("2".equals(st)) {
            return "审核不通过";
        } else if ("3".equals(st)) {
            return "交易成功";
        } else {
            return "已取消";
        }
    }

    public static String getFormatedDateTime(long dateTime) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    public static String getFormatedDateTime(String dateTime) {
        long a = Long.parseLong(dateTime);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(a + 0));
    }
}

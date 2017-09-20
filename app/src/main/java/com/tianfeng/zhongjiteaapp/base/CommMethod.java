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
                ToastManager.showToastReal(fail);
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
                ToastManager.showToastReal(fail);
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
            return "质押待审核";
        } else if ("1".equals(st)) {
            return "质押审核通过";
        } else if ("2".equals(st)) {
            return "质押审核不通过";
        } else if ("3".equals(st)) {
            return "已质押";
        } else if ("4".equals(st)) {
            return "质押取消";
        } else if ("5".equals(st)) {
            return "赎回待审核";
        } else if ("6".equals(st)) {
            return "赎回审核通过";
        } else if ("7".equals(st)) {
            return "已赎回";
        }else if ("8".equals(st)) {
            return "已暂存";
        }else if ("9".equals(st)) {
            return "已提现";
        }else {
            return "已转让";
        }
    }
}

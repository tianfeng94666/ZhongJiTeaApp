package com.tianfeng.zhongjiteaapp.base;

import android.content.Context;

import com.google.gson.Gson;
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
    public static void collected(Context context, String id) {
        Map map = new HashMap();
        map.put("userId", Global.UserId);
        map.put("goodsId", id);
        L.e("map", map.toString());
        VolleyRequestUtils.getInstance().getRequestPost(context, AppURL.COLLECTED_URL, new VolleyRequestUtils.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.e("result", result);
                CollectedResult collectedResult = new Gson().fromJson(result, CollectedResult.class);
                if (Global.RESULT_CODE.equals(collectedResult.getCode())) {

                }
            }

            @Override
            public void onFail(String fail) {
                L.e("fail", fail);
                ToastManager.showToastReal(fail);
            }
        }, map);
    }
}

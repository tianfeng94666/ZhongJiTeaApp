package com.tianfeng.zhongjiteaapp.net;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.BaseApplication;
import com.tianfeng.zhongjiteaapp.base.Global;
import com.tianfeng.zhongjiteaapp.utils.L;
import com.tianfeng.zhongjiteaapp.utils.ToastManager;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;
import com.tianfeng.zhongjiteaapp.viewutils.LoadingWaitDialog;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * volley get post请求
 *
 * @author wenjundu 2015-07-03
 */
public class VolleyRequestUtils {
    public static void cleanCookie() {
        CookieStringtRequest.cookie = "";
        CookieStringtRequest.isTruncate = true;
    }

    private VolleyRequestUtils() {

    }

    private LoadingWaitDialog loadingDialog;

    protected void baseShowWatLoading(Context context) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingWaitDialog(context, "加载中");
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

    static VolleyRequestUtils mInstance;

    public static VolleyRequestUtils getInstance() {
        if (mInstance == null) {
            synchronized (OKHttpRequestUtils.class) {
                if (mInstance == null) {
                    mInstance = new VolleyRequestUtils();
                }
            }
        }
        return mInstance;
    }


    //获取网页或者特殊数据的时候
    public static void GetCookieRequestPurePage(Context context, String url, final HttpStringRequsetCallBack callBack) {

        CookieStringtRequest jsonObjectRequest = new CookieStringtRequest(Method.GET, url, null, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (callBack != null)
                    callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callBack != null) {
                    callBack.onFail(error.toString());
                }
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setCookie();
        BaseApplication.requestQueue.add(jsonObjectRequest);
    }


    //！！！！！！！！get请求为了保证cookie一致  后来不要使用该方法！！！！！！！！！！
    public void getRequestPost(Context context, String url, final HttpStringRequsetCallBack callback, final Map<String, String> map) {
        if(!UIUtils.getNetConnecState(context)){
            ToastManager.showToastReal("请检查网络连接！");
        }
        baseShowWatLoading(context);
        JSONObject jsonObject = new JSONObject(map);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, url, jsonObject, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                baseHideWatLoading();
                if (callback != null)
                    callback.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                baseHideWatLoading();
                if (callback != null)
                    callback.onFail(error.toString());
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
                headers.put("Cookie", "JSESSIONID=" + Global.JESSIONID);
                L.e("cookie=", headers.toString());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return map;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setTag(context);
        BaseApplication.requestQueue.add(jsonObjectRequest);
    }

    public void getStringPostRequest(Context context, String url, final HttpStringRequsetCallBack callback, final Map<String, String> map) {
        if(!UIUtils.getNetConnecState(context)){
            ToastManager.showToastReal("请检查网络连接！");
        }
        baseShowWatLoading(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        baseHideWatLoading();
                        if (callback != null)
                            callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                baseHideWatLoading();
                if (callback != null)
                    callback.onFail(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Cookie", "JSESSIONID=" + Global.JESSIONID);
//                headers.put("content-type","application/json");
                L.e("cookie=", headers.toString());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setTag(context);
        BaseApplication.requestQueue.add(stringRequest);
    }

    //！！！！！！！！get请求！！！！！！！！！！
    public void getRequestGet(Context context, String url, final HttpStringRequsetCallBack callback) {
//        baseShowWatLoading(context);
        if(!UIUtils.getNetConnecState(context)){
            ToastManager.showToastReal("请检查网络连接！");
        }
        JsonObjectRequest jsonObjectRequest = new NormalPostRequest(Request.Method.GET, url, null,
                new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                      //  baseHideWatLoading();
                        if (callback != null)
                            callback.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
             //   baseHideWatLoading();
                if (callback != null)
                    callback.onFail(error.toString());
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Cookie", "JSESSIONID=" + Global.JESSIONID);
                L.e("cookie=", headers.toString());
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setTag(context);
        BaseApplication.requestQueue.add(jsonObjectRequest);
    }

    /**
     * 以GET或者POST方式发送jsonObjectRequest
     *
     * @param context    android应用上下文
     * @param requestUrl 请求的Url
     * @paramrequestParamete 请求的参数，如果为null，则使用GET方法调用，否则使用POST方法调用
     */
    public void sendJsonObjectRequest(Context context, String requestUrl, final HttpRequestCallBack callBack) {
        JsonObjectRequest request = new JsonObjectRequest(requestUrl, null, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                callBack.onSuccess(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callBack.onFailure(volleyError.toString());
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }


    /**
     * @param context  volley加载图片
     * @param imageUrl
     * @param view
     * @return
     */
    public ImageLoader.ImageContainer loadImage(Context context, String imageUrl, ImageView view, int default_image, int error_image) {
        ImageLoader.ImageListener imgListener = ImageLoader.getImageListener(view, default_image, error_image);
        return VolleySingleton.getInstance(context).getImageLoader().get(imageUrl, imgListener);
        //networkImageView.setDefaultImageResId(R.drawable.default_image);
        //networkImageView.setErrorImageResId(R.drawable.failed_image);
    }


    //新加接口 ，保证cookie一致的volley网络请求回调这个接口
    public interface HttpStringRequsetCallBack {
        void onSuccess(String result);

        void onFail(String fail);
    }

    public interface HttpRequestCallBack {
        void onSuccess(JSONObject jsonObject);

        void onFailure(String fail);
    }


}

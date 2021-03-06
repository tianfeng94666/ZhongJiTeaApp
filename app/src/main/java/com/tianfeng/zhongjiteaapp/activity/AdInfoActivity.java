package com.tianfeng.zhongjiteaapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.base.AppURL;
import com.tianfeng.zhongjiteaapp.base.BaseActivity;
import com.tianfeng.zhongjiteaapp.json.AdBean;
import com.tianfeng.zhongjiteaapp.json.Product;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class AdInfoActivity extends BaseActivity {
    @Bind(R.id.id_ig_back)
    ImageView idIgBack;
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.tv_right)
    ImageView tvRight;
    @Bind(R.id.id_rel_title)
    RelativeLayout idRelTitle;
    @Bind(R.id.my_web_view)
    WebView myWebView;
    private String url;
    private AdBean adBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        UIUtils.setBarTint(this, false);
        ButterKnife.bind(this);
        getData(getIntent());
        initView();
    }

    private void getData(Intent intent) {
        Bundle bundle = intent.getExtras();
        adBean = (AdBean) bundle.getSerializable("adBean");
        url = adBean.getUrl();

    }

    private void initView() {
        idIgBack.setVisibility(View.VISIBLE);
        titleText.setText(adBean.getTitle());
        myWebView.loadUrl(url);
        //加载本地中的html
        //myWebView.loadUrl("file:///android_asset/www/test2.html");
        //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
        myWebView.setWebViewClient(new WebViewClient());
        //得到webview设置
        WebSettings webSettings = myWebView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //将WebAppInterface于javascript绑定
//        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webSettings.setPluginState(WebSettings.PluginState.ON);
        myWebView.setWebChromeClient(new WebChromeClient());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
}

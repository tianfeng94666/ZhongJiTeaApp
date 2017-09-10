package com.tianfeng.zhongjiteaapp.viewutils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.widget.TextView;

import com.tianfeng.zhongjiteaapp.R;
import com.tianfeng.zhongjiteaapp.utils.UIUtils;

public class CountTimerButton extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    private TextView tv;

    public CountTimerButton(TextView btn, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.tv =btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv.setClickable(false);  //不可点击
        tv.setText(millisUntilFinished / 1000 + " s");
        int a = UIUtils.dip2px(5);
        tv.setPadding(a,a,a,a);
//        tv.setGravity(Gravity.CENTER);
//        tv.setBackgroundResource(R.drawable.board_gray);
//
//        SpannableString spannableString=new SpannableString(tv.getText().toString());
//        ForegroundColorSpan span=new ForegroundColorSpan(Color.WHITE);
//        spannableString.setSpan(span,0,2, Spanned.SPAN_INCLUSIVE_INCLUSIVE); //时间设为红色
//        tv.setText(spannableString);
    }

    @Override
    public void onFinish() {
        tv.setText("获取验证码");
        tv.setClickable(true);//重新获得点击
        int a = UIUtils.dip2px(5);
        tv.setPadding(a,a,a,a);
//        tv.setGravity(Gravity.CENTER);
//        tv.setBackgroundResource(R.drawable.board_light_oregon);  //还原背景色
    }
}

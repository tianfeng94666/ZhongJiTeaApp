package com.tianfeng.zhongjiteaapp.base;

/**
 * Created by 田丰 on 2017/9/7.
 */

public class AppURL {
    private  static  String baseHost = "http://www.bestfeng.xin:81/xcb";
    /**
     * 登入
     */
    public static String LOGIN_URL= baseHost+"/api/user/login";
    /**
     * 用户注册,登录名，手机号，密码必输
     * api/user/register/{bizId}/{code}
     rest风格请求地址
     bizId：验证码请求返回的bizId
     code：验证码

     */
    public static String REGISTER_URL= baseHost+"/api/user/register";
    /**
     * 获取短信验证码
     * api/sms/send
     */
    public static String GET_MESSAGE_URL= baseHost+"/api/sms/send";
    /**
     * 查询商城商品列表
     */
    public static String GET_PRODUCT_LIST =baseHost+"/api/goods/page";
}

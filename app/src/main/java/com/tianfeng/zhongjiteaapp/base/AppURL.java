package com.tianfeng.zhongjiteaapp.base;

/**
 * Created by 田丰 on 2017/9/7.
 */

public class AppURL {
    //正式
    public static String baseHost = "http://119.23.209.140:81/xcb";
    /**
     * 测试服务器
     */
//    public static String baseHost = "http://119.23.209.140:82/xcb";
    /**
     * 登入
     */
    public static String LOGIN_URL = baseHost + "/api/user/login";
    /**
     * 退出登入
     */
    public static String LOGOUT_URL = baseHost + "/api/user/logout";
    /**
     * 用户注册,登录名，手机号，密码必输
     * api/user/register/{bizId}/{code}
     * rest风格请求地址
     * bizId：验证码请求返回的bizId
     * code：验证码
     */
    public static String REGISTER_URL = baseHost + "/api/user/register";
    /**
     * 修改密码
     */
    public static String RESETPASSWORD_URL = baseHost + "/api/user/reset_password";
    /**
     * 短信验证
     */
    public static String MESSAGE_CHECK = baseHost + "/api/sms/verify";
    /**
     * 获取注册协议
     */
    public static String GET_PROTOCOL_URL = baseHost + "/api/help/protocol/";
    /**
     * 注册获取短信验证码
     * api/sms/send
     */
    public static String GET_MESSAGE_REGISTER_URL = baseHost + "/api/sms/send/register/";
    /**
     * 密码找回获取短信验证码
     * api/sms/send
     */
    public static String GET_MESSAGE_PASSWORD_URL = baseHost + "/api/sms/send/password/";
    /**
     * 查询主页商品列表
     */
    public static String GET_PRODUCT_LIST = baseHost + "/api/goods/page";
    /**
     * 查询商城商品列表
     */
    public static String GET_MALL_PRODUCT_LIST = baseHost + "/api/goods/shop/page";
    /**
     * 查询单个商品信息
     */
    public static String GET_PRODUCT_INFO_URL = baseHost + "/api/goods/";
    /**
     * 广告查询
     */
    public static String GET_ADS_URL = baseHost + "/api/ads/page";
    /**
     * 公告查询
     */
    public static String GET_NOTICE_URL = baseHost + "/api/notice/page";
    /**
     * 地区查询
     */
    public static String GET_AEAR_URL = baseHost+"/api/shop/area";
    /**
     * 门店查询
     */
    public static String GET_SHOPS_LIST = baseHost + "/api/shop/all";
    /**
     * 上传头像
     */
    public static String UPLOAD_PIC = baseHost + "/api/user/head_img/upload/";
    /**
     * 修改昵称
     */
    public static String CHANGE_NAME_URL = baseHost + "/api/user/nick_update/";
    /**
     * 收藏
     */
    public static String COLLECTED_URL = baseHost + "/api/user/goods/like";
    /**
     * 取消收藏
     */
    public static String UNCOLLECTED_URL = baseHost + "/api/user/goods/unlike";
    /**
     * 门店查询
     */
    public static String SHOPINFO_URL = baseHost + "/api/shop/";
    /**
     * 帮助和协议查询
     */
    public static String HELP_URL = baseHost + "/api/help/types";
    /**
     * app信息
     */
    public static String APPINFO_URL = baseHost + "/api/app/info";
    /**
     * 查询用户信息
     */
    public static String USERINFO_URL = baseHost + "/api/user/info/";
    /**
     * 根据分类id查询协议
     */
    public static String ITEM_HELP_URL = baseHost + "/api/help/type/";
    /**
     * 查询收藏
     */
    public static String MY_COLLETED = baseHost + "/api/user/goods/likes/";
    /**
     * 仓储数据查询
     */
    public static String STORAGE_LIST_URL = baseHost + "/api/store/list";
    /**
     * 质押申请
     */
    public static String PLEDGE_URL = baseHost + "/api/store/zy/apply";
    /**
     * 质押确定
     */
    public static String PLEDGE_CONFIRM_URL = baseHost + "/api/store/zy/confirm";
    /**
     * 质押取消
     */
    public static String PLEDGE_CANCLE_URL = baseHost + "/api/store/zy/cancel";
    /**
     * 赎回申请
     */
    public static String RETURN_URL = baseHost + "/api/store/sh/apply";
    /**
     * 赎回确认
     */
    public static String RETURN_CONFIRM = baseHost + "/api/store/sh/confirm";
    /**
     * 赎回取消
     */
    public static String RETURN_CANCLE = baseHost + "/api/store/sh/cancel";
    /**
     * 订单查询
     */
    public static String ORDER_SEARCH = baseHost + "/api/order/list";
    /**
     * 暂存申请
     */
    public static String STORAGE_REQUEST = baseHost + "/api/store/zc/apply";
    /**
     * 现提申请
     */
    public static String XIANTI_REQUEST=baseHost+"/api/store/xt/apply";
    /**
     * 转让申请
     */
    public static String CHANGE_REQUEST= baseHost+"/api/store/zr/apply";
    /**
     * 转让取消
     */
    public static String CHANGE_CANCLE_REQUEST= baseHost+"/api/store/zr/cancel";
    /**
     * 续存申请
     */
    public static String DEPOSIT_REQUEST = baseHost+"/api/store/xc/apply";
    /**
     * 获取仓储数量
     */
    public static String GET_STORAGE = baseHost +"/api/store/count";
    /**
     * 绑定门店
     */
    public static String SET_SHOPID = baseHost +"/api/user/shop/bind";
    /**
     * 暂存消息
     */
    public static String GET_MESSAGELIST= baseHost +"/api/msg/zc";
}

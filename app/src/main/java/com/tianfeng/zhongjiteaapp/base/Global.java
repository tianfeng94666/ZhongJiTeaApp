package com.tianfeng.zhongjiteaapp.base;

/**
 * Created by 田丰 on 2017/9/2.
 */

public class Global {
    public static int selectPosition=0;
    public static String  RESULT_CODE = "0000";
    public static String  FAIL_CODE = "-1";
    public static String JESSIONID;
    public static String BIZID;
    public static String CODE;//验证码
    public static String shopId;
    public static String UserId;
    public static String PhoneNumber;
    public static boolean isLogin =false;
    public static String HeadView;
    public static String nickName;
    public static String AeraFirst;
    public static String AeraSecond;
    public static String AeraCode;
    //记录是否有门店
    public static boolean isHaveShop= true;
    public static String appId = "wxce488c9ce08c20e3";
    public static String secret = "203e4b15ebfc1a03475c2ad0809667ee";
    public static String QQ_APP_ID="1106334471";
    /**
     * 状态编码
     */
    public static  String XIANTI = "0000";//现提
    public static  String STORAGE = "0001";//暂存
    public static  String REBACK = "0002";//赎回
    public static  String CHANGE = "0003";//转让
    public static  String PLEDGE = "0004";//质押
    /**
     * 订单状态
     */
    public static String SHENHE_NO_THROUGH = "2";
    public static String SHENHE_THROUGH = "1";
    public static String FINISH = "3";
    public static String SHENHE_CANCLE= "4";
    public static String SHENHE_WAIT = "0";

    public static String QQ_OPENID;
    public static String WX_OPENID;
}

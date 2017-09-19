package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class AboutResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : {"customer_service_phone":"123456","app_introduction":"小茶宝","app_ios_version":"v1.0","app_android_version":"v1.0"}
     * jsessionid : 0a0d7eed-cdc0-4b63-8ddc-ed10c72681f1
     */

    private String code;
    private String msg;
    private ResultBean result;
    private String jsessionid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public static class ResultBean {
        /**
         * customer_service_phone : 123456
         * app_introduction : 小茶宝
         * app_ios_version : v1.0
         * app_android_version : v1.0
         */

        private String customer_service_phone;
        private String app_introduction;
        private String app_ios_version;
        private String app_android_version;

        public String getCustomer_service_phone() {
            return customer_service_phone;
        }

        public void setCustomer_service_phone(String customer_service_phone) {
            this.customer_service_phone = customer_service_phone;
        }

        public String getApp_introduction() {
            return app_introduction;
        }

        public void setApp_introduction(String app_introduction) {
            this.app_introduction = app_introduction;
        }

        public String getApp_ios_version() {
            return app_ios_version;
        }

        public void setApp_ios_version(String app_ios_version) {
            this.app_ios_version = app_ios_version;
        }

        public String getApp_android_version() {
            return app_android_version;
        }

        public void setApp_android_version(String app_android_version) {
            this.app_android_version = app_android_version;
        }
    }
}

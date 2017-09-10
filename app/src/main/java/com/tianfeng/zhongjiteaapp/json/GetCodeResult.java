package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class GetCodeResult {
    /**
     * code : 0000
     * msg : 发送成功!
     * result : {"bizId":"495106205035220045^0","getMessage":"OK","getCode":"OK"}
     * jsessionid : 6501cd09-447c-455d-b3eb-f3b20ec7676f
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
         * bizId : 495106205035220045^0
         * getMessage : OK
         * getCode : OK
         */

        private String bizId;
        private String getMessage;
        private String getCode;

        public String getBizId() {
            return bizId;
        }

        public void setBizId(String bizId) {
            this.bizId = bizId;
        }

        public String getGetMessage() {
            return getMessage;
        }

        public void setGetMessage(String getMessage) {
            this.getMessage = getMessage;
        }

        public String getGetCode() {
            return getCode;
        }

        public void setGetCode(String getCode) {
            this.getCode = getCode;
        }
    }
}

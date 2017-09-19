package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by 田丰 on 2017/9/19.
 */

public class UserInfoResult {

    /**
     * code : 0000
     * msg : 查询成功!
     * result : {"id":"e4b370f4fc2b4530ab26436aca136e6c","loginName":"13136285056","userName":"13136285056","nickName":"13136285056","mobile":"13163285056","shopId":"d7e9125805014e25913eb41eadfcb9e5","imgUrl":"static/amz/assets/img/user01.png","registerTime":1505554313000}
     * jsessionid : f3680114-4940-4e81-b4da-6b8ea577a15b
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
         * id : e4b370f4fc2b4530ab26436aca136e6c
         * loginName : 13136285056
         * userName : 13136285056
         * nickName : 13136285056
         * mobile : 13163285056
         * shopId : d7e9125805014e25913eb41eadfcb9e5
         * imgUrl : static/amz/assets/img/user01.png
         * registerTime : 1505554313000
         */

        private String id;
        private String loginName;
        private String userName;
        private String nickName;
        private String mobile;
        private String shopId;
        private String imgUrl;
        private long registerTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public long getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(long registerTime) {
            this.registerTime = registerTime;
        }
    }
}

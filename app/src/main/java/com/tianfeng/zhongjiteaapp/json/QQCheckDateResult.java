package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class QQCheckDateResult {


    /**
     * code : 0000
     * msg : 登录成功!
     * result : {"id":"3a0affd0f68a4e23b63bc60e3ba766ae","loginName":null,"userName":null,"nickName":"起风了","userType":"0004","userId":null,"password":"f3e3fbd3d3a1263e19f8343552db4c0e","passwordReal":null,"mobile":"13689500606","areaId":null,"shopId":null,"imgUrl":"http://q.qlogo.cn/qqapp/1106334471/E09E5732FDEF19A7D63A3938A5CB7888/100","registerTime":1513756109000,"isLock":"1","isDisabled":"1","role":null,"permission":null,"lastLoginTime":null,"menu":null,"shopName":null,"wx":null,"qq":"E09E5732FDEF19A7D63A3938A5CB7888"}
     * jsessionid : ac192600-2344-40b1-8682-9c77259b0126
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
         * id : 3a0affd0f68a4e23b63bc60e3ba766ae
         * loginName : null
         * userName : null
         * nickName : 起风了
         * userType : 0004
         * userId : null
         * password : f3e3fbd3d3a1263e19f8343552db4c0e
         * passwordReal : null
         * mobile : 13689500606
         * areaId : null
         * shopId : null
         * imgUrl : http://q.qlogo.cn/qqapp/1106334471/E09E5732FDEF19A7D63A3938A5CB7888/100
         * registerTime : 1513756109000
         * isLock : 1
         * isDisabled : 1
         * role : null
         * permission : null
         * lastLoginTime : null
         * menu : null
         * shopName : null
         * wx : null
         * qq : E09E5732FDEF19A7D63A3938A5CB7888
         */

        private String id;
        private String loginName;
        private String userName;
        private String nickName;
        private String userType;
        private String userId;
        private String password;
        private String passwordReal;
        private String mobile;
        private String areaId;
        private String shopId;
        private String imgUrl;
        private long registerTime;
        private String isLock;
        private String isDisabled;
        private String role;
        private String permission;
        private String lastLoginTime;
        private String menu;
        private String shopName;
        private String wx;
        private String qq;

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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordReal() {
            return passwordReal;
        }

        public void setPasswordReal(String passwordReal) {
            this.passwordReal = passwordReal;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
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

        public String getIsLock() {
            return isLock;
        }

        public void setIsLock(String isLock) {
            this.isLock = isLock;
        }

        public String getIsDisabled() {
            return isDisabled;
        }

        public void setIsDisabled(String isDisabled) {
            this.isDisabled = isDisabled;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }
    }
}

package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class LoginResult {
    /**
     * code : 0000
     * msg : 登录成功!
     * result : {"id":"58f5aad5dc4b4cf985b8f7850aa2e6bf","loginName":"13689500606","userName":"13689500606","nickName":"小猪","userType":"0004","userId":null,"password":null,"passwordReal":null,"mobile":"13689500606","areaId":null,"shopId":"50f9c434f8cb4659acad40c9980595c5","imgUrl":"/file/head/image/img_1505722592324.jpg","registerTime":null,"isLock":"1","isDisabled":"1","role":null,"permission":null,"lastLoginTime":null,"menu":null}
     * jsessionid : 63dd3839-4e7d-4489-98c6-b856af5cd56e
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
         * id : 58f5aad5dc4b4cf985b8f7850aa2e6bf
         * loginName : 13689500606
         * userName : 13689500606
         * nickName : 小猪
         * userType : 0004
         * userId : null
         * password : null
         * passwordReal : null
         * mobile : 13689500606
         * areaId : null
         * shopId : 50f9c434f8cb4659acad40c9980595c5
         * imgUrl : /file/head/image/img_1505722592324.jpg
         * registerTime : null
         * isLock : 1
         * isDisabled : 1
         * role : null
         * permission : null
         * lastLoginTime : null
         * menu : null
         */

        private String id;
        private String loginName;
        private String userName;
        private String nickName;
        private String userType;
        private Object userId;
        private Object password;
        private Object passwordReal;
        private String mobile;
        private Object areaId;
        private String shopId;
        private String imgUrl;
        private Object registerTime;
        private String isLock;
        private String isDisabled;
        private Object role;
        private Object permission;
        private Object lastLoginTime;
        private Object menu;

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

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getPasswordReal() {
            return passwordReal;
        }

        public void setPasswordReal(Object passwordReal) {
            this.passwordReal = passwordReal;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getAreaId() {
            return areaId;
        }

        public void setAreaId(Object areaId) {
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

        public Object getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(Object registerTime) {
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

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public Object getPermission() {
            return permission;
        }

        public void setPermission(Object permission) {
            this.permission = permission;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Object getMenu() {
            return menu;
        }

        public void setMenu(Object menu) {
            this.menu = menu;
        }
    }
}

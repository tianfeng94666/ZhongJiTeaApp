package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class UploadImageResult {

    /**
     * code : 0000
     * msg : 图像上传成功
     * result : {"imgUrl":"/usr/local/apache-tomcat-8.0.46/webapps/xcb//file/head/image/img_1505701233784.jpg","userId":"-1"}
     * jsessionid : 2d8353a9-3177-491d-91a0-3682f2666b1c
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
         * imgUrl : /usr/local/apache-tomcat-8.0.46/webapps/xcb//file/head/image/img_1505701233784.jpg
         * userId : -1
         */

        private String imgUrl;
        private String userId;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}

package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by 田丰 on 2017/9/24.
 */

public class LoginProtocolResutl {

    /**
     * code : 0000
     * jsessionid : 45099c55-675b-43cb-b09d-443a1ce3ea7c
     * msg : 查询成功
     * result : [{"content":" sss","createTime":1505956675000,"id":"79dd089076ce4f6da8f9ae4ceb5243ef","isReadme":"1","title":"注册协议","type":"0001"},{"content":"sss","createTime":1505957265000,"id":"b2d18c52e7024c459360f81977a40cb8","isReadme":"1","title":"仓储管理协议","type":"0002"}]
     */

    private String code;
    private String jsessionid;
    private String msg;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content :  sss
         * createTime : 1505956675000
         * id : 79dd089076ce4f6da8f9ae4ceb5243ef
         * isReadme : 1
         * title : 注册协议
         * type : 0001
         */

        private String content;
        private long createTime;
        private String id;
        private String isReadme;
        private String title;
        private String type;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsReadme() {
            return isReadme;
        }

        public void setIsReadme(String isReadme) {
            this.isReadme = isReadme;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class ItemHelpResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : [{"id":"d6dbc33f19c941b19b4525e4b7e34233","title":"测试","content":"<pre style=\"background-color:#c7edcc;color:#000000;font-family:&#39;Consolas&#39;;font-size:12.0pt;\"><span style=\"background-color:#ffe899;\">help<\/span><\/pre><p><br/><\/p>","type":"0001","createTime":1505579454000,"updateTime":null}]
     * jsessionid : 4867e430-80a0-483d-8d85-d06daad1273d
     */

    private String code;
    private String msg;
    private String jsessionid;
    private List<ResultBean> result;

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

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : d6dbc33f19c941b19b4525e4b7e34233
         * title : 测试
         * content : <pre style="background-color:#c7edcc;color:#000000;font-family:&#39;Consolas&#39;;font-size:12.0pt;"><span style="background-color:#ffe899;">help</span></pre><p><br/></p>
         * type : 0001
         * createTime : 1505579454000
         * updateTime : null
         */

        private String id;
        private String title;
        private String content;
        private String type;
        private long createTime;
        private Object updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}

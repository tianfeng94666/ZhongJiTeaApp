package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class HelpResult {
    /**
     * code : 0000
     * msg : 查询成功
     * result : [{"typeName":"注册登录问题","typeId":"0001"},{"typeName":"仓库类问题","typeId":"0002"},{"typeName":"茶叶质押协议","typeId":"0003"},{"typeName":"茶叶赎回协议","typeId":"0004"}]
     * jsessionid : f3680114-4940-4e81-b4da-6b8ea577a15b
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
         * typeName : 注册登录问题
         * typeId : 0001
         */

        private String typeName;
        private String typeId;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}

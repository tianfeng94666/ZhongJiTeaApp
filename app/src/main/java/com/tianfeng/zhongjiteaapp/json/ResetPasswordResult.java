package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class ResetPasswordResult {

    /**
     * code : 0000
     * msg : 密码修改成功
     * result : null
     * jsessionid : aa6f548c-bf54-495c-aa5b-7623d7de7699
     */

    private String code;
    private String msg;
    private Object result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}

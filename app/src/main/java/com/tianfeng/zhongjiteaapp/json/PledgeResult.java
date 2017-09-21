package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class PledgeResult {

    /**
     * code : 0000
     * msg : 申请成功!
     * result : null
     * jsessionid : 0ce873d6-f6ed-4752-999b-2863ff946a9d
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
